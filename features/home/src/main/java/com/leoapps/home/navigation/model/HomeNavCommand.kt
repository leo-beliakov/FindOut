package com.leoapps.home.navigation.model

import com.leoapps.form.domain.model.FormId

sealed interface HomeNavCommand {
    data class OpenQuizDetails(
        val quizId: FormId
    ) : HomeNavCommand
}