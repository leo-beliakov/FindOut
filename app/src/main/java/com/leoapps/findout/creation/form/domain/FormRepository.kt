package com.leoapps.findout.creation.form.domain

import com.leoapps.findout.creation.form.domain.model.Survey
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface FormRepository {
    fun getFormDraftAsFlow(): Flow<Survey>
    fun updateFormDraft(survey: Survey)
    fun updateDescription(description: String)
    fun updateTitle(title: String)
    fun addQuestion(question: Survey.Question)
    fun deleteQuestionById(id: UUID)
}