package com.leoapps.creation.form.presentation

import com.leoapps.form.domain.model.FormType

data class FormCreationArgs(
    val type: FormType = FormType.QUIZ
)