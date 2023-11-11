package com.leoapps.findout.add_question.presentation

import androidx.lifecycle.ViewModel
import com.leoapps.findout.add_answer.presentation.model.AnswerDialogState
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiAction
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiState
import com.leoapps.findout.add_question.presentation.model.QuestionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(getInitialState())

    private fun getInitialState(): AddQuestionUiState {
        return AddQuestionUiState(
            selectedQuestionType = QuestionType.SINGLE_CHOICE,
            avaliableQuestionTypes = listOf(
                QuestionType.SINGLE_CHOICE,
                QuestionType.MULTIPLE_CHOICES,
                QuestionType.OPEN_ANSWER
            ),
        )
    }

    val state = _state.asStateFlow()

    fun onAction(action: AddQuestionUiAction) {
        when (action) {
            AddQuestionUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            AddQuestionUiAction.AddImageClicked -> {

            }

            AddQuestionUiAction.AddAnswerClicked -> {
                _state.update {
                    it.copy(dialogState = AnswerDialogState.Create)
                }
            }

            AddQuestionUiAction.OnDialogDismissed -> {
                _state.update {
                    it.copy(dialogState = null)
                }
            }

            is AddQuestionUiAction.OnAnswerClicked -> {
                _state.update {
                    it.copy(
                        dialogState = AnswerDialogState.Edit(answer = action.answer)
                    )
                }
            }

            AddQuestionUiAction.CloseClicked -> {

            }

            AddQuestionUiAction.OnAddQuestionClicked -> {

            }

            is AddQuestionUiAction.DescriptionUpdated -> {
                _state.update { it.copy(description = action.newValue) }
            }

            is AddQuestionUiAction.TitleUpdated -> {
                _state.update { it.copy(title = action.newValue) }
            }

            is AddQuestionUiAction.OnTypeSelected -> {
                _state.update {
                    it.copy(
                        selectedQuestionType = action.type
                    )
                }
            }

            is AddQuestionUiAction.OnAddAnswerClicked -> {
                _state.update {
                    val answerModel = AddQuestionUiState.Answer(
                        id = UUID.randomUUID(),
                        title = action.answer,
                    )
                    it.copy(
                        answers = it.answers + answerModel,
                        dialogState = null
                    )
                }
            }

            is AddQuestionUiAction.OnEditAnswerClicked -> {
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
}