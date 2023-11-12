package com.leoapps.findout.creation.form.presentation.model

import android.net.Uri
import androidx.annotation.StringRes
import com.leoapps.findout.R

data class FormCreationUiState(
    @StringRes val pageNameResId: Int = R.string.form_creation_title_quiz,
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