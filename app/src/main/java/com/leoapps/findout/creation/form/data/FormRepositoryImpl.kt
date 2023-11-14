package com.leoapps.findout.creation.form.data

import com.leoapps.findout.creation.form.domain.FormRepository
import com.leoapps.findout.creation.form.domain.model.Form
import com.leoapps.findout.creation.form.domain.model.FormType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor() : FormRepository {
    private val _cachedSurvey = MutableStateFlow<Form?>(null)
    private val cachedSurvey = _cachedSurvey.filterNotNull()
    override fun createNewForm(type: FormType) {
        _cachedSurvey.value = Form(
            id = UUID.randomUUID(),
            type = type
        )
    }

    override fun getFormDraftAsFlow(): Flow<Form> {
        return cachedSurvey
    }

    override fun updateFormDraft(survey: Form) {
        _cachedSurvey.value = survey
    }

    override fun updateDescription(description: String) {
        _cachedSurvey.update { it?.copy(description = description) }
    }

    override fun updateTitle(title: String) {
        _cachedSurvey.update { it?.copy(title = title) }
    }

    override fun saveQuestion(question: Form.Question) {
        _cachedSurvey.update { survey ->
            survey?.copy(questions = survey.questions.addOrUpdate(question))
        }
    }

    override fun deleteQuestionById(id: UUID) {
        _cachedSurvey.update { survey ->
            val updatedQuestions = survey?.questions?.toMutableList()
                ?.filter { it.id != id }
                ?: emptyList()
            survey?.copy(questions = updatedQuestions)
        }
    }

    override fun getQuestionById(id: UUID): Form.Question? {
        return _cachedSurvey.value?.questions?.firstOrNull { it.id == id }
    }
}

private fun List<Form.Question>.addOrUpdate(question: Form.Question): List<Form.Question> {
    val updatedQuestions = this.toMutableList()
    val index = updatedQuestions.indexOfFirst { it.id == question.id }
    if (index == -1) {
        updatedQuestions.add(question)
    } else {
        updatedQuestions.removeAt(index)
        updatedQuestions.add(index, question)
    }

    return updatedQuestions.toList()
}
