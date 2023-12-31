package com.leoapps.form.domain.model

import android.net.Uri
import java.util.UUID

data class Form(
    val id: UUID,
    val type: FormType,
    val title: String? = null,
    val description: String? = null,
    val coverUri: Uri? = null,
    val questions: List<Question> = emptyList()
) {
    sealed class Question(
        open val id: UUID,
        open val title: String,
        open val coverUri: Uri? = null,
        open val description: String?,
        open val type: QuestionType
    ) {
        data class Choice(
            val isSingleChoice: Boolean,
            val answers: List<Answer>,
            override val id: UUID,
            override val title: String,
            override val coverUri: Uri?,
            override val description: String?,
        ) : Question(
            id = id,
            title = title,
            coverUri = coverUri,
            description = description,
            type = if (isSingleChoice) QuestionType.SINGLE_CHOICE else QuestionType.MULTIPLE_CHOICES
        )

        data class Open(
            override val id: UUID,
            override val title: String,
            override val coverUri: Uri?,
            override val description: String?,
        ) : Question(
            id = id,
            title = title,
            coverUri = coverUri,
            description = description,
            type = QuestionType.OPEN_ANSWER
        )

        class Answer(
            val id: UUID,
            val title: String,
        )
    }
}
