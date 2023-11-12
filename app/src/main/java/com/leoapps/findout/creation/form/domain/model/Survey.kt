package com.leoapps.findout.creation.form.domain.model

import android.net.Uri
import java.util.UUID

data class Survey(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val coverUri: Uri? = null,
    val questions: List<Question> = emptyList()
) {
    sealed class Question(
        open val id: UUID,
        open val title: String,
        open val description: String?,
    ) {
        data class Choice(
            val isSingleChoice: Boolean,
            val answers: List<Answer>,
            override val id: UUID,
            override val title: String,
            override val description: String?,
        ) : Question(id, title, description)

        data class Open(
            override val title: String,
            override val description: String?,
            override val id: UUID,
        ) : Question(id, title, description)

        class Answer(
            val id: String,
            val title: String,
        )
    }
}