package com.leoapps.creation.form.domain

import com.leoapps.form.domain.FormRepository
import javax.inject.Inject

class SaveFormUseCase @Inject constructor(
    private val formRepository: FormRepository,
    private val creationRepository: FormCreationRepository
) {

    suspend operator fun invoke() {
        val formDraft = creationRepository.getFormDraft() ?: return
        formRepository.saveForm(formDraft)
    }
}