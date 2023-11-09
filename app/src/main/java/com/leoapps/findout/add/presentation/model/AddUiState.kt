package com.leoapps.findout.add.presentation.model

import android.net.Uri

data class AddUiState(
    val pageName: String = "Create Survey",
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val questions: List<Question> = emptyList(),
) {
    data class Question(
        val id: Long,
        val title: String,
    )
}