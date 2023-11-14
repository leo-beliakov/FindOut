package com.leoapps.findout.creation.form.navigation

import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.findout.creation.question.presentation.QuestionCreationArgs
import com.leoapps.findout.destinations.QuestionCreationScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface FormCreationNavigator {
    fun onNavigationCommand(command: FormCreationNavCommand)
}

class FormCreationNavigatorImpl(
    private val navController: DestinationsNavigator
) : FormCreationNavigator {
    override fun onNavigationCommand(command: FormCreationNavCommand) {
        when (command) {
            FormCreationNavCommand.GoBack -> navController.navigateUp()
            is FormCreationNavCommand.OpenQuestion -> {
                navController.navigate(
                    QuestionCreationScreenDestination(
                        QuestionCreationArgs(
                            formType = command.formType,
                            questionId = command.questionId
                        )
                    )
                )
            }
        }
    }
}
