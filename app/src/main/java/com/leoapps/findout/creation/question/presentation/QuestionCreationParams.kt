package com.leoapps.findout.creation.question.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class QuestionCreationParams(
    val questionId: UUID? = null
) : Parcelable