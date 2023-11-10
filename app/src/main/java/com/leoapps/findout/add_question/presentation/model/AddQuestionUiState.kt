package com.leoapps.findout.add_question.presentation.model

import android.net.Uri

data class AddQuestionUiState(
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null
//    val type:: String = "",
) {


    sealed class Question {
        class SingleAnswer
        class MultipleAnswer
        class TextAnswer
    }
}