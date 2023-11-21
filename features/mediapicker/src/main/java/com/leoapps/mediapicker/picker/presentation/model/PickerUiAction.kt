package com.leoapps.mediapicker.picker.presentation.model

import android.net.Uri
import androidx.compose.ui.geometry.Rect

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction
    object OnCancelClicked : PickerUiAction

    data class OnImageSelected(
        val id: Long
    ) : PickerUiAction

    data class OnImageClicked(
        val uri: Uri,
        val elementBounds: Rect,
        val index: Int
    ) : PickerUiAction
}