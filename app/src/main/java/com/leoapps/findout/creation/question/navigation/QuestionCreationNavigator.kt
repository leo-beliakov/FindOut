package com.leoapps.findout.creation.question.navigation

import androidx.navigation.NavController
import com.leoapps.findout.creation.question.navigation.model.QuestionCreationNavCommand

interface QuestionCreationNavigator {
    fun onNavCommand(command: QuestionCreationNavCommand)
}

class QuestionCreationNavigatorImpl(
    private val navController: NavController
) : QuestionCreationNavigator {
    override fun onNavCommand(command: QuestionCreationNavCommand) {
        when (command) {
            QuestionCreationNavCommand.GoBack -> navController.popBackStack()
        }
    }
}