package com.leoapps.findout.add_answer.presentation.model

import androidx.annotation.StringRes
import com.leoapps.findout.R
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiState

sealed class AnswerDialogState(
    @StringRes val titleResId: Int,
    @StringRes val buttonTextResId: Int,
    val answerText: String,
) {
    object Create : AnswerDialogState(
        titleResId = R.string.answer_dialog_title_add,
        buttonTextResId = R.string.answer_dialog_button_add,
        answerText = ""
    )

    class Edit(
        val answer: AddQuestionUiState.Answer,
    ) : AnswerDialogState(
        titleResId = R.string.answer_dialog_title_edit,
        buttonTextResId = R.string.answer_dialog_button_edit,
        answerText = answer.title,
    )
}