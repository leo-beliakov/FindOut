package com.leoapps.creation.question.navigation

import com.leoapps.creation.question.navigation.model.QuestionCreationNavCommand
import com.leoapps.mediapicker.root.presentation.MediapickerNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface QuestionCreationNavigator {
    fun onNavCommand(command: QuestionCreationNavCommand)
}

class QuestionCreationNavigatorImpl(
    private val navigator: DestinationsNavigator
) : QuestionCreationNavigator {

    override fun onNavCommand(command: QuestionCreationNavCommand) {
        when (command) {
            QuestionCreationNavCommand.GoBack -> navigator.popBackStack()
            QuestionCreationNavCommand.OpenImagePicker -> navigator.navigate(MediapickerNavGraph)
        }
    }
}