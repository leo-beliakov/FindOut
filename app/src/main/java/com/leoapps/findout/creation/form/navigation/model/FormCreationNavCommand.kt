package com.leoapps.findout.creation.form.navigation.model

sealed interface FormCreationNavCommand {
    object GoBack : FormCreationNavCommand
    data class OpenAddQuestionScreen(
        val isEdit: Boolean
    ) : FormCreationNavCommand
}