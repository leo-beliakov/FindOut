package com.leoapps.creation.question.presentation

import android.os.Parcelable
import com.leoapps.form.domain.model.FormType
import com.leoapps.form.domain.model.QuestionId
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionCreationArgs(
    val formType: FormType,
    val questionId: QuestionId? = null
) : Parcelable