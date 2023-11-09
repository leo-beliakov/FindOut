package com.leoapps.findout.add.presentation

import androidx.lifecycle.ViewModel
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.add.presentation.model.AddUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddSurveyScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(AddUiState())
    val state = _state.asStateFlow()
    fun onAction(action: AddUiAction) {
        when (action) {
            AddUiAction.AddDescriptionClicked -> TODO()
            AddUiAction.AddImageClicked -> TODO()
            AddUiAction.AddQuestionClicked -> TODO()
            AddUiAction.BackClicked -> TODO()
        }
    }
}