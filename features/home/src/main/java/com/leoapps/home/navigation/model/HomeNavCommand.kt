package com.leoapps.home.navigation.model

import java.util.UUID

sealed interface HomeNavCommand {
    data class OpenQuizDetails(
        val quizId: UUID
    ) : HomeNavCommand
}