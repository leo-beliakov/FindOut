package com.leoapps.findout.add_question.presentation.model

sealed interface AddQuestionUiAction {
    object CloseClicked : AddQuestionUiAction
    object AddImageClicked : AddQuestionUiAction
    object AddDescriptionClicked : AddQuestionUiAction
    object AddAnswerClicked : AddQuestionUiAction
    object OnAddQuestionClicked : AddQuestionUiAction

    data class OnTypeSelected(
        val type: QuestionType
    ) : AddQuestionUiAction

    data class TitleUpdated(
        val newValue: String
    ) : AddQuestionUiAction

    data class DescriptionUpdated(
        val newValue: String
    ) : AddQuestionUiAction
}