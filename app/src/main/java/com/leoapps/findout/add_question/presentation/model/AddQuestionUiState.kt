package com.leoapps.findout.add_question.presentation.model

import android.net.Uri
import com.leoapps.findout.R

data class AddQuestionUiState(
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val questionType: QuestionType = QuestionType.SINGLE_ANSWER,
    val questionBody: QuestionBody = QuestionBody.SingleAnswer()
) {


    sealed class QuestionBody {
        data class SingleAnswer(
            val correctAnswerId: Long? = null,
            val answers: List<Answer> = emptyList()
        ) : QuestionBody()

        data class MultipleAnswer(
            val correctAnswerIds: List<Long> = emptyList(),
            val answers: List<Answer> = emptyList()
        ) : QuestionBody()

        object TextAnswer : QuestionBody()
    }

    data class Answer(
        val id: Long,
        val title: String,
        val isCorrect: Boolean = false,
    )
}

enum class QuestionType(val nameResId: Int) {
    SINGLE_ANSWER(R.string.question_type_single),
    MULTIPLE_ANSWER(R.string.question_type_multiple),
    OPEN_ANSWER(R.string.question_type_open),
}