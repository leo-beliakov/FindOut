package com.leoapps.creation.question.presentation.model

import android.net.Uri
import androidx.annotation.StringRes
import com.leoapps.creation.R
import com.leoapps.creation.answer.presentation.model.AnswerCreationState
import com.leoapps.form.domain.model.AnswerId
import com.leoapps.form.domain.model.QuestionId

data class QuestionCreationUiState(
    val id: QuestionId = QUESTION_NO_ID,
    val title: String = "",
    val description: String = "",
    val hasDescription: Boolean = false,
    val coverUri: Uri? = null,
    val dialogState: AnswerCreationState? = null,
    val answers: List<Answer> = emptyList(),
    val selectedQuestionType: QuestionTypeUiModel = QuestionTypeUiModel.SINGLE_ANSWER,
    val availableQuestionTypes: List<QuestionTypeUiModel> = emptyList(),
    @StringRes val screenTitleResId: Int = R.string.question_creation_title_add,
    @StringRes val screenButtonResId: Int = R.string.question_creation_button_add,
) {

    data class Answer(
        val id: AnswerId,
        val title: String,
        val isCorrect: Boolean = false,
    )

    companion object {
        //unique ID for new forms is generated in the repository, not in the VM
        private const val QUESTION_NO_ID = -1
    }
}

enum class QuestionTypeUiModel(
    val id: Int,
    @StringRes val textResId: Int
) {
    SINGLE_ANSWER(
        id = 1,
        textResId = R.string.question_type_single
    ),
    SINGLE_CHOICE(
        id = 2,
        textResId = R.string.question_type_single_choice
    ),
    MULTIPLE_ANSWER(
        id = 3,
        textResId = R.string.question_type_multiple
    ),
    MULTIPLE_CHOICES(
        id = 4,
        textResId = R.string.question_type_multiple_choices
    ),
    OPEN_ANSWER(
        id = 5,
        textResId = R.string.question_type_open
    ),
}