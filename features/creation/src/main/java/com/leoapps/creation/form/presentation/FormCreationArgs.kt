package com.leoapps.creation.form.presentation

import com.leoapps.creation.form.domain.model.FormType

data class FormCreationArgs(
    val type: FormType = FormType.QUIZ
)