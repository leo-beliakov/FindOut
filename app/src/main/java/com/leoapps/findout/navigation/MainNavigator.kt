package com.leoapps.findout.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.creation.destinations.QuestionCreationScreenDestination
import com.leoapps.creation.form.navigation.FormCreationNavigator
import com.leoapps.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.creation.question.navigation.QuestionCreationNavigator
import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.creation.question.presentation.QuestionCreationArgs
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand
import com.leoapps.mediapicker.root.navigation.PickerNavigator
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import com.leoapps.mediapicker.root.presentation.MediapickerNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(
    private val navigator: DestinationsNavigator
) : RootNavigator,
    FormCreationNavigator,
    QuestionCreationNavigator,
    PickerNavigator,
    ImageDetailNavigator {

    override fun openAdd() {
        navigator.navigate(CreationFeatureNavGraph)
    }

    override fun onNavigationCommand(command: FormCreationNavCommand) {
        when (command) {
            FormCreationNavCommand.GoBack -> {
                navigator.navigateUp()
            }

            FormCreationNavCommand.OpenImagePicker -> {
                navigator.navigate(MediapickerNavGraph)
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

    override fun onNavCommand(command: PickerNavCommand) {
        when (command) {
            PickerNavCommand.GoBack -> {
                navigator.popBackStack()
            }

            is PickerNavCommand.OpenImageDetail -> {
//                navigator.navigate(ImageDetailScreenDestination(command.uri))
            }
        }
    }

    override fun onNavCommand(command: ImageDetailNavCommand) {
        when (command) {
            ImageDetailNavCommand.GoBack -> navigator.popBackStack()
        }
    }
}