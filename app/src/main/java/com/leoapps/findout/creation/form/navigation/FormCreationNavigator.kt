package com.leoapps.findout.creation.form.navigation

import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.findout.destinations.QuestionCreationScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface FormCreationNavigator {
    fun onNavigationCommand(event: FormCreationNavCommand)
}

class FormCreationNavigatorImpl(
    private val navController: DestinationsNavigator
) : FormCreationNavigator {
    override fun onNavigationCommand(event: FormCreationNavCommand) {
        when (event) {
            FormCreationNavCommand.GoBack -> navController.navigateUp()
            FormCreationNavCommand.OpenAddQuestion -> {
                navController.navigate(QuestionCreationScreenDestination)
            }

            is FormCreationNavCommand.OpenQuestion -> {
                navController.navigate(QuestionCreationScreenDestination)
            }
        }
    }
}
