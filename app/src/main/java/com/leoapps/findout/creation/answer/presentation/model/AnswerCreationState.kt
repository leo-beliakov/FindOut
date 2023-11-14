package com.leoapps.findout.creation.answer.presentation.model

import androidx.annotation.StringRes
import com.leoapps.findout.R
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiState

sealed class AnswerCreationState(
    @StringRes val titleResId: Int,
    @StringRes val buttonTextResId: Int,
    val answerText: String,
) {
    object Create : AnswerCreationState(
        titleResId = R.string.answer_dialog_title_add,
        buttonTextResId = R.string.answer_dialog_button_add,
        answerText = ""
    )

    class Edit(
        val answer: QuestionCreationUiState.Answer,
    ) : AnswerCreationState(
        titleResId = R.string.answer_dialog_title_edit,
        buttonTextResId = R.string.answer_dialog_button_edit,
        answerText = answer.title,
    )
}