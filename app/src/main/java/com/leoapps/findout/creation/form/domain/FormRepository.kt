package com.leoapps.findout.creation.form.domain

import com.leoapps.findout.creation.form.domain.model.Survey
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface FormRepository {
    fun getFormDraftAsFlow(): Flow<Survey>
    fun updateFormDraft(survey: Survey)
    fun updateDescription(description: String)
    fun updateTitle(title: String)
    fun saveQuestion(question: Survey.Question)
    fun deleteQuestionById(id: UUID)
    fun getQuestionById(id: UUID): Survey.Question?
}