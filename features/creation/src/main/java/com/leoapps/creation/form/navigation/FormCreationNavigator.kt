package com.leoapps.creation.form.navigation

import com.leoapps.creation.destinations.QuestionCreationScreenDestination
import com.leoapps.creation.form.navigation.model.FormCreationNavCommand
import com.leoapps.creation.question.presentation.QuestionCreationArgs
import com.leoapps.mediapicker.root.presentation.MediapickerNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import javax.inject.Inject

interface FormCreationNavigator {
    fun onNavigationCommand(command: FormCreationNavCommand)
}

class FormCreationNavigatorImpl @Inject constructor(
    private val navigator: DestinationsNavigator
) : FormCreationNavigator {

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

}
