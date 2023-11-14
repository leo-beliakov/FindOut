package com.leoapps.findout.creation.form.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.findout.R
import com.leoapps.findout.creation.form.domain.FormRepository
import com.leoapps.findout.creation.form.domain.model.Form
import com.leoapps.findout.creation.form.domain.model.FormType
import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiState
import com.leoapps.findout.navArgs
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
    savedStateHandle: SavedStateHandle,
    private val repository: FormRepository
) : ViewModel() {

    val formType = savedStateHandle.navArgs<FormCreationArgs>().type

    private val _state = MutableStateFlow(FormCreationUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<FormCreationNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    init {
        repository.createNewForm(type = formType)
        subscribeToRepository()
    }

    private fun subscribeToRepository() {
        repository.getFormDraftAsFlow()
            .onEach { savedSurvey ->
                _state.update {
                    it.copy(
                        title = savedSurvey.title ?: "",
                        description = savedSurvey.description ?: "",
                        hasDescription = !savedSurvey.description.isNullOrEmpty() || it.hasDescription,
                        questions = savedSurvey.questions.mapToUi(),
                        pageNameResId = when (savedSurvey.type) {
                            FormType.SURVEY -> R.string.form_creation_title_survey
                            FormType.QUIZ -> R.string.form_creation_title_quiz
                        }
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
                        FormCreationNavCommand.OpenQuestion(
                            formType = formType
                        )
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

            is FormCreationUiAction.OnQuestionClicked -> {
                viewModelScope.launch {
                    _navCommand.emit(
                        FormCreationNavCommand.OpenQuestion(
                            formType = formType,
                            questionId = action.question.id
                        )
                    )
                }
            }

            is FormCreationUiAction.OnQuestionDismissed -> {
                repository.deleteQuestionById(action.question.id)
            }
        }
    }
}

private fun List<Form.Question>.mapToUi(): List<FormCreationUiState.Question> {
    return this.map {
        FormCreationUiState.Question(
            id = it.id,
            title = it.title,
        )
    }
}
