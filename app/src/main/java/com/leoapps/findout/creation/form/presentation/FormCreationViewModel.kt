package com.leoapps.findout.creation.form.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormCreationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(FormCreationUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<FormCreationNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun onAction(action: FormCreationUiAction) {
        when (action) {
            FormCreationUiAction.AddDescriptionClicked -> {
                _state.update { it.copy(hasDescription = true) }
            }

            FormCreationUiAction.AddImageClicked -> {

            }

            FormCreationUiAction.AddQuestionClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(
                        FormCreationNavCommand.OpenAddQuestionScreen(isEdit = false)
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

            FormCreationUiAction.BackClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(FormCreationNavCommand.GoBack)
                }
            }

            FormCreationUiAction.OnCreateClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(FormCreationNavCommand.GoBack)
                }
            }

            is FormCreationUiAction.DescriptionUpdated -> {
                _state.update { it.copy(description = action.newValue) }
            }

            is FormCreationUiAction.TitleUpdated -> {
                Log.d("MyTag", "FormCreationUiAction.TitleUpdated ${action.newValue}")
                _state.update { it.copy(title = action.newValue) }
            }
        }
    }
}