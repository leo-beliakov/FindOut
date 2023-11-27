package com.leoapps.mediapicker.picker.presentation.model

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction
    object OnCancelClicked : PickerUiAction

    data class OnImageSelected(
        val id: Long
    ) : PickerUiAction

    data class OnImageClicked(
        val id: Long
    ) : PickerUiAction

    data class OnAlbumSelected(
        val album: PickerUiState.Album
    ) : PickerUiAction
}