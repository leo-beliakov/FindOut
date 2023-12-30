package com.leoapps.form.data.model.mapper

import com.leoapps.form.data.model.AnswerEntity
import com.leoapps.form.data.model.FormEntity
import com.leoapps.form.data.model.QuestionEntity
import com.leoapps.form.domain.model.Form
import com.leoapps.form.domain.model.FormType
import javax.inject.Inject

class FormDataMapper @Inject constructor() {

    fun mapToEntity(form: Form): FormEntity {
        return FormEntity(
            id = form.id,
            type = form.type,
            title = form.title,
            description = form.description,
            coverUri = form.coverUri,
            questions = form.questions.map { mapQuestionToEntity(it) },
        )
    }

    private fun mapQuestionToEntity(question: Form.Question): QuestionEntity {
        return QuestionEntity(
            id = question.id,
            title = question.title,
            coverUri = question.coverUri,
            description = question.description,
            type = question.type,
            isSingleChoice = (question as? Form.Question.Choice)?.isSingleChoice == true,
            answers = (question as? Form.Question.Choice)?.answers?.map { mapAnswerToEntity(it) }
                ?: emptyList(),
        )
    }

    private fun mapAnswerToEntity(answer: Form.Question.Answer): AnswerEntity {
        return AnswerEntity(
            id = answer.id,
            title = answer.title
        )
    }

    fun mapToDomain(form: FormEntity): Form {
        return Form(
            id = form.id,
            type = FormType.QUIZ,
        )
    }
}