package com.leoapps.mediapicker.root.navigation

import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand

interface PickerNavigator {
    fun onNavCommand(command: PickerNavCommand)
}