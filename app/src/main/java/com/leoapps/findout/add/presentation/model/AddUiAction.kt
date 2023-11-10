package com.leoapps.findout.add.presentation.model

sealed interface AddUiAction {
    object BackClicked : AddUiAction
    object AddImageClicked : AddUiAction
    object AddDescriptionClicked : AddUiAction
    object AddQuestionClicked : AddUiAction
    object OnCreateClicked : AddUiAction

    data class TitleUpdated(
        val newValue: String
    ) : AddUiAction

    data class DescriptionUpdated(
        val newValue: String
    ) : AddUiAction
}