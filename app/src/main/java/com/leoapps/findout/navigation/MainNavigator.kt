package com.leoapps.findout.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.creation.destinations.QuestionCreationScreenDestination
import com.leoapps.creation.form.navigation.FormCreationNavigator
import com.leoapps.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.creation.question.navigation.QuestionCreationNavigator
import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.creation.question.presentation.QuestionCreationArgs
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.media_picker.presentation.PickerNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(
    private val navigator: DestinationsNavigator
) : RootNavigator,
    FormCreationNavigator,
    QuestionCreationNavigator {

    override fun openAdd() {
        navigator.navigate(CreationFeatureNavGraph)
    }

    override fun onNavigationCommand(command: FormCreationNavCommand) {
        when (command) {
            FormCreationNavCommand.GoBack -> {
                navigator.navigateUp()
            }

            FormCreationNavCommand.OpenImagePicker -> {
                navigator.navigate(PickerNavGraph)
            }

            is FormCreationNavCommand.OpenQuestion -> {
                navigator.navigate(
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

    override fun onNavCommand(command: QuestionCreationNavCommand) {
        when (command) {
            QuestionCreationNavCommand.GoBack -> navigator.popBackStack()
        }
    }
}