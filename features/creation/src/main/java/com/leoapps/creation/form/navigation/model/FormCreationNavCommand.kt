package com.leoapps.creation.form.navigation.model

import com.leoapps.form.domain.model.FormType
import com.leoapps.form.domain.model.QuestionId

sealed interface FormCreationNavCommand {
    object GoBack : FormCreationNavCommand
    object OpenImagePicker : FormCreationNavCommand
    data class OpenQuestion(
        val formType: FormType,
        val questionId: QuestionId? = null
    ) : FormCreationNavCommand
}