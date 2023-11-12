package com.leoapps.findout.creation.question.presentation.model

sealed interface QuestionCreationUiAction {
    object CloseClicked : QuestionCreationUiAction
    object AddImageClicked : QuestionCreationUiAction
    object AddDescriptionClicked : QuestionCreationUiAction
    object AddAnswerClicked : QuestionCreationUiAction
    object OnAddQuestionClicked : QuestionCreationUiAction
    object OnDialogDismissed : QuestionCreationUiAction

    data class OnTypeSelected(
        val type: QuestionType
    ) : QuestionCreationUiAction

    data class TitleUpdated(
        val newValue: String
    ) : QuestionCreationUiAction

    data class DescriptionUpdated(
        val newValue: String
    ) : QuestionCreationUiAction

    data class OnAddAnswerClicked(
        val answer: String
    ) : QuestionCreationUiAction

    data class OnAnswerClicked(
        val answer: QuestionCreationUiState.Answer
    ) : QuestionCreationUiAction

    data class OnEditAnswerClicked(
        val answer: QuestionCreationUiState.Answer
    ) : QuestionCreationUiAction

    data class OnAnswerDismissed(
        val answer: QuestionCreationUiState.Answer
    ) : QuestionCreationUiAction
}