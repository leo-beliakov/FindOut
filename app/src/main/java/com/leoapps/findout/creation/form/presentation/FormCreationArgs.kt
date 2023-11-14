package com.leoapps.findout.creation.form.presentation

import com.leoapps.findout.creation.form.domain.model.FormType

data class FormCreationArgs(
    val type: FormType = FormType.QUIZ
)