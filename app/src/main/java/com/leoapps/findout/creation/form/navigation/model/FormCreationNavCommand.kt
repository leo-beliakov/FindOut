package com.leoapps.findout.creation.form.navigation.model

import java.util.UUID

sealed interface FormCreationNavCommand {
    object GoBack : FormCreationNavCommand
    object OpenAddQuestion : FormCreationNavCommand

    data class OpenQuestion(
        val questionId: UUID
    ) : FormCreationNavCommand
}