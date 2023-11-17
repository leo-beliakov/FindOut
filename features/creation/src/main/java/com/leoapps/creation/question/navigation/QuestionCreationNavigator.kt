package com.leoapps.creation.question.navigation

import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand

interface QuestionCreationNavigator {
    fun onNavCommand(command: QuestionCreationNavCommand)
}