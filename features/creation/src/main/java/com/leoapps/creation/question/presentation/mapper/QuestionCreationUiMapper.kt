package com.leoapps.creation.question.presentation.mapper

import com.leoapps.creation.question.presentation.model.QuestionTypeUiModel
import com.leoapps.form.domain.model.QuestionType
import javax.inject.Inject

class QuestionCreationUiMapper @Inject constructor() {

    fun map(type: QuestionType): QuestionTypeUiModel {
        return when (type) {
            QuestionType.SINGLE_ANSWER -> QuestionTypeUiModel.SINGLE_ANSWER
            QuestionType.SINGLE_CHOICE -> QuestionTypeUiModel.SINGLE_CHOICE
            QuestionType.MULTIPLE_ANSWER -> QuestionTypeUiModel.MULTIPLE_ANSWER
            QuestionType.MULTIPLE_CHOICES -> QuestionTypeUiModel.MULTIPLE_CHOICES
            QuestionType.OPEN_ANSWER -> QuestionTypeUiModel.OPEN_ANSWER
        }
    }
}