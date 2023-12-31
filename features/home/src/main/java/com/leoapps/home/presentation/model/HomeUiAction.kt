package com.leoapps.home.presentation.model

import java.util.UUID

sealed interface HomeUiAction {
    data class OnQuizClicked(
        val id: UUID
    )
}