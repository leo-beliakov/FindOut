package com.leoapps.creation.form.data

import android.net.Uri
import com.leoapps.creation.form.domain.FormCreationRepository
import com.leoapps.form.domain.model.Form
import com.leoapps.form.domain.model.FormType
import com.leoapps.form.domain.model.QuestionId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FormCreationRepositoryImpl @Inject constructor() : FormCreationRepository {
    private val _cachedSurvey = MutableStateFlow<Form?>(null)
    private val cachedSurvey = _cachedSurvey.filterNotNull()

    override fun createNewForm(type: FormType) {
        _cachedSurvey.value = Form(
            id = DRAFT_ID,
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

    override fun deleteQuestionById(id: QuestionId) {
        _cachedSurvey.update { survey ->
            val updatedQuestions = survey?.questions?.toMutableList()
                ?.filter { it.id != id }
                ?: emptyList()
            survey?.copy(questions = updatedQuestions)
        }
    }

    override fun getQuestionById(id: QuestionId): Form.Question? {
        return _cachedSurvey.value?.questions?.firstOrNull { it.id == id }
    }

    override fun updateImage(uri: Uri) {
        _cachedSurvey.update { it?.copy(coverUri = uri) }
    }

    override suspend fun getFormDraft(): Form? {
        return _cachedSurvey.value
    }

    companion object {
        private const val DRAFT_ID = 0
    }
}

private fun List<Form.Question>.addOrUpdate(question: Form.Question): List<Form.Question> {
    val updatedQuestions = this.toMutableList()

    val index = updatedQuestions.indexOfFirst { it.id == question.id }
    if (index == -1) {
        val updatedQuestion = question.withUpdatedId(updatedQuestions.size)
        updatedQuestions.add(updatedQuestion)
    } else {
        updatedQuestions.removeAt(index)
        updatedQuestions.add(index, question)
    }

    return updatedQuestions.toList()
}

private fun Form.Question.withUpdatedId(newId: QuestionId): Form.Question {
    return when (this) {
        is Form.Question.Choice -> this.copy(id = newId)
        is Form.Question.Open -> this.copy(id = newId)
    }
}
