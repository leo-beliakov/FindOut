package com.leoapps.findout.add_question.navigation

import androidx.navigation.NavController
import com.leoapps.findout.add_question.navigation.model.AddQuestionNavCommand

interface AddQuestionNavigator {
    fun onNavCommand(command: AddQuestionNavCommand)
}

class AddQuestionNavigatorImpl(
    private val navController: NavController
) : AddQuestionNavigator {
    override fun onNavCommand(command: AddQuestionNavCommand) {
        when (command) {
            AddQuestionNavCommand.GoBack -> navController.popBackStack()
        }
    }
}