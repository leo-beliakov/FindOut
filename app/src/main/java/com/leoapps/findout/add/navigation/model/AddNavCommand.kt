package com.leoapps.findout.add.navigation.model

sealed interface AddNavCommand {
    object GoBack : AddNavCommand
    data class OpenAddQuestionScreen(
        val isEdit: Boolean
    ) : AddNavCommand
}