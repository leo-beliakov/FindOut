package com.leoapps.mediapicker.root.navigation.model

import android.net.Uri
import androidx.compose.ui.geometry.Rect

sealed interface PickerNavCommand {
    object GoBack : PickerNavCommand

    data class OpenImageDetail(
        val uri: Uri,
        val startBounds: Rect
    ) : PickerNavCommand
}