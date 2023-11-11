package com.leoapps.findout.add_question.presentation.model

import android.net.Uri
import com.leoapps.findout.R
import com.leoapps.findout.add_answer.presentation.model.AnswerDialogState
import java.util.UUID

data class AddQuestionUiState(
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val answerDialogState: AnswerDialogState? = null,
    val answers: List<Answer> = emptyList(),
    val questionType: QuestionType = QuestionType.SINGLE_ANSWER,
    val questionTypes: List<QuestionType> = emptyList()
) {

    sealed class QuestionBody(
        val questionType: QuestionType
    ) {
        data class SingleAnswer(
            val correctAnswerId: Long? = null,
            val answers: List<Answer> = emptyList()
        ) : QuestionBody(QuestionType.SINGLE_ANSWER)

        data class SingleChoice(
            val answers: List<Answer> = emptyList()
        ) : QuestionBody(QuestionType.SINGLE_CHOICE)

        data class MultipleChoices(
            val answers: List<Answer> = emptyList()
        ) : QuestionBody(QuestionType.MULTIPLE_CHOICES)

        data class MultipleAnswer(
            val correctAnswerIds: List<Long> = emptyList(),
            val answers: List<Answer> = emptyList()
        ) : QuestionBody(QuestionType.MULTIPLE_ANSWER)

        object TextAnswer : QuestionBody(QuestionType.OPEN_ANSWER)
    }

    data class Answer(
        val id: UUID,
        val title: String,
        val isCorrect: Boolean = false,
    )
}

enum class QuestionType(val nameResId: Int) {
    SINGLE_ANSWER(R.string.question_type_single),
    SINGLE_CHOICE(R.string.question_type_single_choice),
    MULTIPLE_ANSWER(R.string.question_type_multiple),
    MULTIPLE_CHOICES(R.string.question_type_multiple_choices),
    OPEN_ANSWER(R.string.question_type_open),
}