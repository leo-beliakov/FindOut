package com.leoapps.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.form.domain.FormRepository
import com.leoapps.home.navigation.model.HomeNavCommand
import com.leoapps.home.presentation.model.HomeUiAction
import com.leoapps.home.presentation.model.HomeUiState
import com.leoapps.home.presentation.model.QuizUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FormRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<HomeNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    init {
        repository.getAllFormsAsFlow()
            .onEach { forms ->
                _state.update {
                    it.copy(
                        quizzes = forms.map { form ->
                            QuizUiModel(
                                id = form.id,
                                name = form.title
                            )
                        }
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(homeUiAction: HomeUiAction) {

    }
}
