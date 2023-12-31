package com.leoapps.form.data.model

import com.leoapps.form.data.model.converters.UuidSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AnswerEntity(
    @Serializable(with = UuidSerializer::class)
    val id: UUID,
    val title: String,
)