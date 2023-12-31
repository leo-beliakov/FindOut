package com.leoapps.home.presentation.model

import java.util.UUID

data class HomeUiState(
    val quizzes: List<QuizUiModel> = emptyList()
)

data class QuizUiModel(
    val id: UUID,
    val name: String?
)