package com.leoapps.findout.add_question.presentation

import androidx.lifecycle.ViewModel
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiAction
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(AddQuestionUiState())
    val state = _state.asStateFlow()

    fun onAction(action: AddQuestionUiAction) {
        when (action) {
            AddQuestionUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            AddQuestionUiAction.AddImageClicked -> {

            }

            AddQuestionUiAction.AddAnswerClicked -> {

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
//                _state.update { it.copy(title = action.newValue) }
            }
        }
    }

}