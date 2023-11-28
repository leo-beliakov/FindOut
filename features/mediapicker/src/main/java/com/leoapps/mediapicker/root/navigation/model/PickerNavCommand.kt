package com.leoapps.mediapicker.root.navigation.model

import android.net.Uri

sealed interface PickerNavCommand {
    object GoBack : PickerNavCommand
    data class GoBackWithResult(
        val imageUri: Uri
    ) : PickerNavCommand
}