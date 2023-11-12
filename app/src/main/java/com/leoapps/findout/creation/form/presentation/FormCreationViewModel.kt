package com.leoapps.findout.creation.form.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.findout.creation.form.domain.FormRepository
import com.leoapps.findout.creation.form.domain.model.Survey
import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormCreationViewModel @Inject constructor(
    private val repository: FormRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FormCreationUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<FormCreationNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    init {
        repository.getFormDraftAsFlow()
            .onEach { savedSurvey ->
                Log.d("MyTag", "onEach ${savedSurvey.questions.size}")
                _state.update {
                    it.copy(
                        title = savedSurvey.title ?: "",
                        description = savedSurvey.description ?: "",
                        hasDescription = !savedSurvey.description.isNullOrEmpty() || it.hasDescription,
                        questions = savedSurvey.questions.mapToUi()
                    )
                }
            }
            .launchIn(viewModelScope)
    }


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
                repository.updateDescription(action.newValue)
            }

            is FormCreationUiAction.TitleUpdated -> {
                repository.updateTitle(action.newValue)
            }
        }
    }
}

private fun List<Survey.Question>.mapToUi(): List<FormCreationUiState.Question> {
    return this.map {
        FormCreationUiState.Question(
            id = it.id.leastSignificantBits,
            title = it.title,
        )
    }
}
