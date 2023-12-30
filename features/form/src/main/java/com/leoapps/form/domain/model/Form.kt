package com.leoapps.form.domain.model

import android.net.Uri

data class Form(
    val id: FormId,
    val type: FormType,
    val title: String? = null,
    val description: String? = null,
    val coverUri: Uri? = null,
    val questions: List<Question> = emptyList()
) {
    sealed class Question(
        open val id: QuestionId,
        open val title: String,
        open val coverUri: Uri? = null,
        open val description: String?,
        open val type: QuestionType
    ) {
        data class Choice(
            val isSingleChoice: Boolean,
            val answers: List<Answer>,
            override val id: QuestionId,
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
            override val id: QuestionId,
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
            val id: AnswerId,
            val title: String,
        )
    }
}

//Typealiases for convenience in case we want to change the types
typealias FormId = Int
typealias QuestionId = Int
typealias AnswerId = Int
