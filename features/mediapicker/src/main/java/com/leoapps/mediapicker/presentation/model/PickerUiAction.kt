package com.leoapps.mediapicker.presentation.model

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction
    object OnCancelClicked : PickerUiAction

    data class OnImageSelected(
        val id: Long
    ) : PickerUiAction

    data class OnImageClicked(
        val id: Long
    ) : PickerUiAction
}