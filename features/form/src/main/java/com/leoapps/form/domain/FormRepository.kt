package com.leoapps.form.domain

import com.leoapps.form.domain.model.Form
import kotlinx.coroutines.flow.Flow

interface FormRepository {
    suspend fun saveForm(form: Form)
    fun getAllFormsAsFlow(): Flow<List<Form>>
}