package com.leoapps.creation.form.domain

import android.net.Uri
import com.leoapps.creation.form.domain.model.Form
import com.leoapps.creation.form.domain.model.FormType
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface FormRepository {
    fun createNewForm(type: FormType)
    fun getFormDraftAsFlow(): Flow<Form>
    fun updateFormDraft(survey: Form)
    fun updateDescription(description: String)
    fun updateTitle(title: String)
    fun saveQuestion(question: Form.Question)
    fun deleteQuestionById(id: UUID)
    fun getQuestionById(id: UUID): Form.Question?
    fun updateImage(uri: Uri)
}