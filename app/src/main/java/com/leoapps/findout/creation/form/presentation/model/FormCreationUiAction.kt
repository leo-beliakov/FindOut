package com.leoapps.findout.creation.form.presentation.model

sealed interface FormCreationUiAction {
    object BackClicked : FormCreationUiAction
    object AddImageClicked : FormCreationUiAction
    object AddDescriptionClicked : FormCreationUiAction
    object AddQuestionClicked : FormCreationUiAction
    object OnCreateClicked : FormCreationUiAction

    data class TitleUpdated(
        val newValue: String
    ) : FormCreationUiAction

    data class DescriptionUpdated(
        val newValue: String
    ) : FormCreationUiAction
}