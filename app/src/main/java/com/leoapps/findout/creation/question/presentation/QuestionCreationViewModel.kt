package com.leoapps.findout.creation.question.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.findout.creation.answer.presentation.model.AnswerCreationState
import com.leoapps.findout.creation.form.domain.FormRepository
import com.leoapps.findout.creation.form.domain.model.Survey
import com.leoapps.findout.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiState
import com.leoapps.findout.creation.question.presentation.model.QuestionType
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
    private val repository: FormRepository
) : ViewModel() {

    private val _state = MutableStateFlow(getInitialState())

    private fun getInitialState(): QuestionCreationUiState {
        return QuestionCreationUiState(
            selectedQuestionType = QuestionType.SINGLE_CHOICE,
            avaliableQuestionTypes = listOf(
                QuestionType.SINGLE_CHOICE,
                QuestionType.MULTIPLE_CHOICES,
                QuestionType.OPEN_ANSWER
            ),
        )
    }

    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<QuestionCreationNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun onAction(action: QuestionCreationUiAction) {
        when (action) {
            QuestionCreationUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            QuestionCreationUiAction.AddImageClicked -> {

            }

            QuestionCreationUiAction.AddAnswerClicked -> {
                _state.update {
                    it.copy(dialogState = AnswerCreationState.Create)
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
                        dialogState = AnswerCreationState.Edit(answer = action.answer)
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
                    repository.addQuestion(getQuestionModel())
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
        }
    }

    private fun getQuestionModel(): Survey.Question {
        val currentState = state.value
        return when (currentState.selectedQuestionType) {
            QuestionType.SINGLE_CHOICE,
            QuestionType.MULTIPLE_CHOICES -> {
                Survey.Question.Choice(
                    id = UUID.randomUUID(),
                    title = currentState.title,
                    description = currentState.description,
                    isSingleChoice = currentState.selectedQuestionType == QuestionType.SINGLE_CHOICE,
                    answers = getAnswersModels(currentState.answers),
                )
            }

            else -> {
                Survey.Question.Open(
                    id = UUID.randomUUID(),
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
    ): List<Survey.Question.Answer> {
        return answers.map {
            Survey.Question.Answer(
                id = it.id.toString(),
                title = it.title,
            )
        }
    }
}