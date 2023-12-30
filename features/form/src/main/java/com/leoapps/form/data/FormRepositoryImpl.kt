package com.leoapps.form.data

import com.leoapps.form.data.model.mapper.FormDataMapper
import com.leoapps.form.domain.FormRepository
import com.leoapps.form.domain.model.Form
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FormRepositoryImpl @Inject constructor(
    private val dao: FormDao,
    private val mapper: FormDataMapper
) : FormRepository {
    override suspend fun saveForm(form: Form) {
        dao.saveForm(mapper.mapToEntity(form))
    }

    override fun getAllFormsAsFlow(): Flow<List<Form>> {
        return dao.getAllFormsAsFlow()
            .map { forms ->
                forms.map { form ->
                    mapper.mapToDomain(form)
                }
            }
    }
}