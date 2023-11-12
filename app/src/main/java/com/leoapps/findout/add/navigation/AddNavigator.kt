package com.leoapps.findout.add.navigation

import androidx.navigation.NavController
import com.leoapps.findout.add.navigation.model.AddNavCommand

interface AddNavigator {
    fun onNavigationCommand(event: AddNavCommand)
}

class AddNavigatorImpl(
    val navController: NavController
) : AddNavigator {
    override fun onNavigationCommand(event: AddNavCommand) {
        when (event) {
            AddNavCommand.GoBack -> navController.popBackStack()
            is AddNavCommand.OpenAddQuestionScreen -> navController.navigate("add_question")
        }
    }
}
