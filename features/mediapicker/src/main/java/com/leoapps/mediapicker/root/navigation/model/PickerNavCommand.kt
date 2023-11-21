package com.leoapps.mediapicker.root.navigation.model

sealed interface PickerNavCommand {
    object GoBack : PickerNavCommand
}