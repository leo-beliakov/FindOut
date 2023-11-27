package com.leoapps.creation.form.presentation.model

import android.net.Uri

sealed interface FormCreationUiAction {
    object BackClicked : FormCreationUiAction
    object AddImageClicked : FormCreationUiAction
    object AddDescriptionClicked : FormCreationUiAction
    object AddQuestionClicked : FormCreationUiAction
    object OnCreateClicked : FormCreationUiAction
    data class OnImageSelected(
        val uri: Uri
    ) : FormCreationUiAction

    data class TitleUpdated(
        val newValue: String
    ) : FormCreationUiAction

    data class DescriptionUpdated(
        val newValue: String
    ) : FormCreationUiAction

    data class OnQuestionClicked(
        val question: FormCreationUiState.Question
    ) : FormCreationUiAction

    data class OnQuestionDismissed(
        val question: FormCreationUiState.Question
    ) : FormCreationUiAction
}