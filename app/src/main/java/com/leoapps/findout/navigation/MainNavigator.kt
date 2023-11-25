package com.leoapps.findout.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.creation.question.navigation.QuestionCreationNavigator
import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand
import com.leoapps.mediapicker.root.navigation.PickerNavigator
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(
    private val navigator: DestinationsNavigator
) : RootNavigator,
    QuestionCreationNavigator,
    PickerNavigator,
    ImageDetailNavigator {

    override fun openAdd() {
        navigator.navigate(CreationFeatureNavGraph)
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
        }
    }

    override fun onNavCommand(command: ImageDetailNavCommand) {
        when (command) {
            ImageDetailNavCommand.GoBack -> navigator.popBackStack()
        }
    }
}