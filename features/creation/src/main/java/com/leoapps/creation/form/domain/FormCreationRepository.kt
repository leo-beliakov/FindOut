package com.leoapps.creation.form.domain

import android.net.Uri
import com.leoapps.form.domain.model.Form
import com.leoapps.form.domain.model.FormType
import com.leoapps.form.domain.model.QuestionId
import kotlinx.coroutines.flow.Flow

interface FormCreationRepository {
    fun createNewForm(type: FormType)
    fun getFormDraftAsFlow(): Flow<Form>
    fun updateFormDraft(survey: Form)
    fun updateDescription(description: String)
    fun updateTitle(title: String)
    fun saveQuestion(question: Form.Question)
    fun deleteQuestionById(id: QuestionId)
    fun getQuestionById(id: QuestionId): Form.Question?
    fun updateImage(uri: Uri)
    suspend fun getFormDraft(): Form?
}