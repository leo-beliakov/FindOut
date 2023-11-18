package com.leoapps.media_picker.presentation.model

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction

    data class OnImageSelected(
        val id: Long
    ) : PickerUiAction
}