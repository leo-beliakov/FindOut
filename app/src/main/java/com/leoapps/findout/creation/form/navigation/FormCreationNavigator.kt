package com.leoapps.findout.creation.form.navigation

import androidx.navigation.NavController
import com.leoapps.findout.creation.form.navigation.model.FormCreationNavCommand

interface FormCreationNavigator {
    fun onNavigationCommand(event: FormCreationNavCommand)
}

class FormCreationNavigatorImpl(
    private val navController: NavController
) : FormCreationNavigator {
    override fun onNavigationCommand(event: FormCreationNavCommand) {
        when (event) {
            FormCreationNavCommand.GoBack -> navController.navigateUp()
            is FormCreationNavCommand.OpenAddQuestionScreen -> navController.navigate("question_creature")
        }
    }
}
