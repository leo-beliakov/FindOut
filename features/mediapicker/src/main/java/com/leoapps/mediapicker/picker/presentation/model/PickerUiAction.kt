package com.leoapps.mediapicker.picker.presentation.model

import android.net.Uri

sealed interface PickerUiAction {
    object OnGalleryPermissionGranted : PickerUiAction
    object OnCancelClicked : PickerUiAction

    data class OnImageSelected(
        val uri: Uri
    ) : PickerUiAction

    data class OnImageClicked(
        val id: Long
    ) : PickerUiAction

    data class OnAlbumSelected(
        val album: PickerUiState.Album
    ) : PickerUiAction
}