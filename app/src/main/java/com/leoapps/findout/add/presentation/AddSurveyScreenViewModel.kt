package com.leoapps.findout.add.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.findout.add.navigation.model.AddNavCommand
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.add.presentation.model.AddUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSurveyScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(AddUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<AddNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun onAction(action: AddUiAction) {
        when (action) {
            AddUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            AddUiAction.AddImageClicked -> {

            }

            AddUiAction.AddQuestionClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(
                        AddNavCommand.OpenAddQuestionScreen(isEdit = false)
                    )
                }
//                _state.update {
//                    it.copy(
//                        questions = it.questions + AddUiState.Question(
//                            id = it.questions.size.toLong(),
//                            title = "This is a new question ${it.questions.size}"
//                        )
//                    )
//                }
            }

            AddUiAction.BackClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(AddNavCommand.GoBack)
                }
            }

            AddUiAction.OnCreateClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(AddNavCommand.GoBack)
                }
            }

            is AddUiAction.DescriptionUpdated -> {
                _state.update { it.copy(description = action.newValue) }
            }

            is AddUiAction.TitleUpdated -> {
                _state.update { it.copy(title = action.newValue) }
            }
        }
    }
}