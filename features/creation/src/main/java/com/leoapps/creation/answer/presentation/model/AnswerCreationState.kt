package com.leoapps.creation.answer.presentation.model

import androidx.annotation.StringRes
import com.leoapps.creation.R
import com.leoapps.creation.question.presentation.model.QuestionCreationUiState

sealed class AnswerCreationState(
    @StringRes val titleResId: Int,
    @StringRes val buttonTextResId: Int,
    val answerText: String,
    val isCorrect: Boolean,
    val isCorrectShown: Boolean,
) {
    class Create(
        isCorrectShown: Boolean
    ) : AnswerCreationState(
        titleResId = R.string.answer_dialog_title_add,
        buttonTextResId = R.string.answer_dialog_button_add,
        answerText = "",
        isCorrect = false,
        isCorrectShown = isCorrectShown
    )

    class Edit(
        isCorrectShown: Boolean,
        val answer: QuestionCreationUiState.Answer,
    ) : AnswerCreationState(
        titleResId = R.string.answer_dialog_title_edit,
        buttonTextResId = R.string.answer_dialog_button_edit,
        answerText = answer.title,
        isCorrect = answer.isCorrect,
        isCorrectShown = isCorrectShown
    )
}