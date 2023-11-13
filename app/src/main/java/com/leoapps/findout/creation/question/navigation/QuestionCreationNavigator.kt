package com.leoapps.findout.creation.question.navigation

import com.leoapps.findout.creation.question.navigation.model.QuestionCreationNavCommand
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface QuestionCreationNavigator {
    fun onNavCommand(command: QuestionCreationNavCommand)
}

class QuestionCreationNavigatorImpl(
    private val navController: DestinationsNavigator
) : QuestionCreationNavigator {
    override fun onNavCommand(command: QuestionCreationNavCommand) {
        when (command) {
            QuestionCreationNavCommand.GoBack -> navController.popBackStack()
        }
    }
}