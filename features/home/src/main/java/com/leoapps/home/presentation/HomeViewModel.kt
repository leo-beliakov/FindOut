package com.leoapps.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.home.domain.QuizRepository
import com.leoapps.home.presentation.model.HomeUiState
import com.leoapps.home.presentation.model.QuizUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        repository.getAllQuizzesAsFlow()
            .onEach {quizzes ->
                _state.update {
                    it.copy(
                        quizzes = quizzes.map {quiz ->
                            QuizUiModel(
                                id = quiz.id,
                                name = quiz.name
                            )
                        }
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}