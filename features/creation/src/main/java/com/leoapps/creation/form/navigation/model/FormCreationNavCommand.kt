package com.leoapps.creation.form.navigation.model

import com.leoapps.creation.form.domain.model.FormType
import java.util.UUID

sealed interface FormCreationNavCommand {
    object GoBack : FormCreationNavCommand
    object OpenImagePicker : FormCreationNavCommand
    data class OpenQuestion(
        val formType: FormType,
        val questionId: UUID? = null
    ) : FormCreationNavCommand
}