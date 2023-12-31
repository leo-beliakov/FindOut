package com.leoapps.form.data.model

import android.net.Uri
import com.leoapps.form.data.model.converters.UriSerializer
import com.leoapps.form.data.model.converters.UuidSerializer
import com.leoapps.form.domain.model.QuestionType
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class QuestionEntity(
    @Serializable(with = UuidSerializer::class)
    val id: UUID,
    val title: String,
    @Serializable(with = UriSerializer::class)
    val coverUri: Uri?,
    val description: String?,
    val type: QuestionType,
    val isSingleChoice: Boolean = false,
    val answers: List<AnswerEntity> = emptyList(),
)