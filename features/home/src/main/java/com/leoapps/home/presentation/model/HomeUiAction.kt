package com.leoapps.home.presentation.model

import com.leoapps.form.domain.model.FormId

sealed interface HomeUiAction {
    data class OnQuizClicked(
        val id: FormId
    )
}