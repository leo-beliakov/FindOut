package com.leoapps.mediapicker.picker.presentation.model

import android.net.Uri

data class PickerUiState(
    val clickedItemId: Long = -1,
    val mediaItems: List<Image> = emptyList(),
) {
    data class Image(
        val id: Long,
        val uri: Uri,
        val isSelected: Boolean = false,
    )
}
