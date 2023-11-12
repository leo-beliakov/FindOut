package com.leoapps.findout.creation.question.presentation.model

import android.net.Uri
import com.leoapps.findout.R
import com.leoapps.findout.creation.answer.presentation.model.AnswerCreationState
import java.util.UUID

data class QuestionCreationUiState(
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val dialogState: AnswerCreationState? = null,
    val answers: List<Answer> = emptyList(),
    val selectedQuestionType: QuestionType = QuestionType.SINGLE_ANSWER,
    val avaliableQuestionTypes: List<QuestionType> = emptyList()
) {

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