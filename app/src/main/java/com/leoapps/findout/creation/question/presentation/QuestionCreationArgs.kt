package com.leoapps.findout.creation.question.presentation

import android.os.Parcelable
import com.leoapps.findout.creation.form.domain.model.FormType
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class QuestionCreationArgs(
    val formType: FormType,
    val questionId: UUID? = null
) : Parcelable