package com.leoapps.creation.form.presentation.model

import android.net.Uri
import androidx.annotation.StringRes
import com.leoapps.creation.R
import com.leoapps.form.domain.model.QuestionId

data class FormCreationUiState(
    @StringRes val pageNameResId: Int = R.string.form_creation_title_survey,
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val questions: List<Question> = emptyList(),
) {
    data class Question(
        val id: QuestionId,
        val title: String,
    )
}