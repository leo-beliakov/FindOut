package com.leoapps.findout.creation.form.data

import com.leoapps.findout.creation.form.domain.FormRepository
import com.leoapps.findout.creation.form.domain.model.Survey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor() : FormRepository {
    private val _cachedSurvey = MutableStateFlow(Survey(id = "DRAFT"))
    val cachedSurvey = _cachedSurvey.asStateFlow()

    override fun getFormDraftAsFlow(): Flow<Survey> {
        return cachedSurvey
    }

    override fun updateFormDraft(survey: Survey) {
        _cachedSurvey.value = survey
    }

    override fun updateDescription(description: String) {
        _cachedSurvey.update { it.copy(description = description) }
    }

    override fun updateTitle(title: String) {
        _cachedSurvey.update { it.copy(title = title) }
    }

    override fun saveQuestion(question: Survey.Question) {
        _cachedSurvey.update { survey ->
            survey.copy(questions = survey.questions.addOrUpdate(question))
        }
    }

    override fun deleteQuestionById(id: UUID) {
        _cachedSurvey.update { survey ->
            val updatedQuestions = survey.questions.toMutableList().filter { it.id != id }
            survey.copy(questions = updatedQuestions)
        }
    }

    override fun getQuestionById(id: UUID): Survey.Question? {
        return _cachedSurvey.value.questions.firstOrNull { it.id == id }
    }
}

private fun List<Survey.Question>.addOrUpdate(question: Survey.Question): List<Survey.Question> {
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
