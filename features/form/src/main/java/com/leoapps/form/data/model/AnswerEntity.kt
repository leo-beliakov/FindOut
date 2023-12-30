package com.leoapps.form.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerEntity(
    val id: Int,
    val title: String,
)