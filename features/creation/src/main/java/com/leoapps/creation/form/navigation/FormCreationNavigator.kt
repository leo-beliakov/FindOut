package com.leoapps.creation.form.navigation

import com.leoapps.creation.destinations.QuestionCreationScreenDestination
import com.leoapps.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.creation.question.presentation.QuestionCreationArgs
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
            FormCreationNavCommand.OpenImagePicker -> navController.navigateUp(
//                PickerBottomSheetDestination
            )

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
