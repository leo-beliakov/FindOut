package com.leoapps.mediapicker.root.presentation.model

import android.net.Uri

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction
    object OnCancelClicked : PickerUiAction

    data class OnImageSelected(
        val id: Long
    ) : PickerUiAction

    data class OnImageClicked(
        val uri: Uri
    ) : PickerUiAction
}