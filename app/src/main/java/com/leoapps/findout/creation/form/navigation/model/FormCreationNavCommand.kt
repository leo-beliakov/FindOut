package com.leoapps.findout.creation.form.navigation.model

import com.leoapps.findout.creation.form.domain.model.FormType
import java.util.UUID

sealed interface FormCreationNavCommand {
    object GoBack : FormCreationNavCommand
    data class OpenQuestion(
        val formType: FormType,
        val questionId: UUID? = null
    ) : FormCreationNavCommand
}