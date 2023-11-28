package com.leoapps.creation.question.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.creation.R
import com.leoapps.creation.answer.presentation.model.AnswerCreationState
import com.leoapps.creation.form.domain.FormRepository
import com.leoapps.creation.form.domain.model.Form
import com.leoapps.creation.form.domain.model.FormType
import com.leoapps.creation.navArgs
import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.creation.question.presentation.model.QuestionCreationUiState
import com.leoapps.creation.question.presentation.model.QuestionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QuestionCreationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: FormRepository
) : ViewModel() {

    private val questionId = savedStateHandle.navArgs<QuestionCreationArgs>().questionId
    val formType = savedStateHandle.navArgs<QuestionCreationArgs>().formType

    private val _state = MutableStateFlow(QuestionCreationUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<QuestionCreationNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    init {
        readArguments()
        readSavedQuestion()
    }

    fun onAction(action: QuestionCreationUiAction) {
        when (action) {
            QuestionCreationUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            QuestionCreationUiAction.AddImageClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(QuestionCreationNavCommand.OpenImagePicker)
                }
            }

            QuestionCreationUiAction.AddAnswerClicked -> {
                _state.update {
                    it.copy(
                        dialogState = AnswerCreationState.Create(
                            isCorrectShown = it.selectedQuestionType == QuestionType.SINGLE_ANSWER ||
                                    it.selectedQuestionType == QuestionType.MULTIPLE_CHOICES,
                        )
                    )
                }
            }

            QuestionCreationUiAction.OnDialogDismissed -> {
                _state.update {
                    it.copy(dialogState = null)
                }
            }

            is QuestionCreationUiAction.OnAnswerClicked -> {
                _state.update {
                    it.copy(
                        dialogState = AnswerCreationState.Edit(
                            answer = action.answer,
                            isCorrectShown = it.selectedQuestionType == QuestionType.SINGLE_ANSWER ||
                                    it.selectedQuestionType == QuestionType.MULTIPLE_CHOICES,
                        )
                    )
                }
            }

            is QuestionCreationUiAction.OnAnswerDismissed -> {
                _state.update {
                    it.copy(
                        answers = it.answers.toMutableList().filter { it.id != action.answer.id },
                    )
                }
            }

            QuestionCreationUiAction.CloseClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(QuestionCreationNavCommand.GoBack)
                }
            }

            QuestionCreationUiAction.OnAddQuestionClicked -> {
                viewModelScope.launch {
                    repository.saveQuestion(getQuestionModel())
                    _navCommand.emit(QuestionCreationNavCommand.GoBack)
                }
            }

            is QuestionCreationUiAction.DescriptionUpdated -> {
                _state.update { it.copy(description = action.newValue) }
            }

            is QuestionCreationUiAction.TitleUpdated -> {
                _state.update { it.copy(title = action.newValue) }
            }

            is QuestionCreationUiAction.OnTypeSelected -> {
                _state.update {
                    it.copy(
                        selectedQuestionType = action.type
                    )
                }
            }

            is QuestionCreationUiAction.OnAddAnswerClicked -> {
                _state.update {
                    val answerModel = QuestionCreationUiState.Answer(
                        id = UUID.randomUUID(),
                        title = action.answer,
                    )
                    it.copy(
                        answers = it.answers + answerModel,
                        dialogState = null
                    )
                }
            }

            is QuestionCreationUiAction.OnEditAnswerClicked -> {
                _state.update {
                    val updatedList = it.answers.toMutableList()
                        .map { item -> if (item.id == action.answer.id) action.answer else item }

                    it.copy(
                        answers = updatedList,
                        dialogState = null
                    )
                }
            }

            is QuestionCreationUiAction.OnImageSelected -> {
                _state.update {
                    it.copy(coverUri = action.uri)
                }
            }
        }
    }

    private fun readArguments() {
        val question = questionId?.let { repository.getQuestionById(it) }
        _state.update {
            it.copy(
                selectedQuestionType = question?.type ?: when (formType) {
                    FormType.SURVEY -> QuestionType.SINGLE_CHOICE
                    FormType.QUIZ -> QuestionType.SINGLE_ANSWER
                },
                availableQuestionTypes = when (formType) {
                    FormType.SURVEY -> listOf(
                        QuestionType.SINGLE_CHOICE,
                        QuestionType.MULTIPLE_CHOICES,
                        QuestionType.OPEN_ANSWER
                    )

                    FormType.QUIZ -> listOf(
                        QuestionType.SINGLE_ANSWER,
                        QuestionType.MULTIPLE_ANSWER,
                        QuestionType.OPEN_ANSWER
                    )
                },
            )
        }
    }

    private fun readSavedQuestion() {
        val question = questionId?.let { repository.getQuestionById(it) }
        question?.let { savedQuestion ->
            _state.update {
                it.copy(
                    id = savedQuestion.id,
                    screenTitleResId = R.string.question_creation_button_edit,
                    screenButtonResId = R.string.question_creation_button_edit,
                    title = savedQuestion.title,
                    description = savedQuestion.description ?: "",
                    coverUri = savedQuestion.coverUri,
                    selectedQuestionType = savedQuestion.type,
                    hasDescription = !savedQuestion.description.isNullOrEmpty(),
                    answers = getAnswersModels(savedQuestion)
                )
            }
        }
    }

    private fun getQuestionModel(): Form.Question {
        val currentState = state.value
        return when (currentState.selectedQuestionType) {
            QuestionType.SINGLE_CHOICE,
            QuestionType.MULTIPLE_CHOICES -> {
                Form.Question.Choice(
                    id = currentState.id,
                    title = currentState.title,
                    description = currentState.description,
                    coverUri = currentState.coverUri,
                    isSingleChoice = currentState.selectedQuestionType == QuestionType.SINGLE_CHOICE,
                    answers = getAnswersModels(currentState.answers),
                )
            }

            else -> {
                Form.Question.Open(
                    id = currentState.id,
                    coverUri = currentState.coverUri,
                    title = currentState.title,
                    description = currentState.description,
                )
            }
//            QuestionType.MULTIPLE_ANSWER -> TODO()
//            QuestionType.SINGLE_ANSWER -> TODO()
//            QuestionType.OPEN_ANSWER -> TODO()
        }
    }

    private fun getAnswersModels(
        answers: List<QuestionCreationUiState.Answer>
    ): List<Form.Question.Answer> {
        return answers.map {
            Form.Question.Answer(
                id = it.id,
                title = it.title,
            )
        }
    }

    private fun getAnswersModels(
        question: Form.Question
    ): List<QuestionCreationUiState.Answer> {
        return when (question) {
            is Form.Question.Choice -> {
                question.answers.map {
                    QuestionCreationUiState.Answer(
                        id = it.id,
                        title = it.title,
                    )
                }
            }

            is Form.Question.Open -> {
                emptyList()
            }
        }
    }
}