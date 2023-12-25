package com.leoapps.creation.question.presentation

import android.os.Parcelable
import com.leoapps.form.domain.model.FormType
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class QuestionCreationArgs(
    val formType: FormType,
    val questionId: UUID? = null
) : Parcelable