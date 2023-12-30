package com.leoapps.home.presentation.model

import com.leoapps.form.domain.model.FormId

data class HomeUiState(
    val quizzes: List<QuizUiModel> = emptyList()
)

data class QuizUiModel(
    val id: FormId,
    val name: String?
)