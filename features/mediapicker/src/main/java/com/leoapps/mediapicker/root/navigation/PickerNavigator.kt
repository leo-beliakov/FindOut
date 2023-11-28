package com.leoapps.mediapicker.root.navigation

import android.net.Uri
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import com.ramcosta.composedestinations.result.ResultBackNavigator

interface PickerNavigator {
    fun onNavCommand(command: PickerNavCommand)
}

class PickerNavigatorImpl(
    private val resultBackNavigator: ResultBackNavigator<Uri>,
) : PickerNavigator {
    override fun onNavCommand(command: PickerNavCommand) {
        when (command) {
            PickerNavCommand.GoBack -> resultBackNavigator.navigateBack()
            is PickerNavCommand.GoBackWithResult -> resultBackNavigator.navigateBack(command.imageUri)
        }
    }

}