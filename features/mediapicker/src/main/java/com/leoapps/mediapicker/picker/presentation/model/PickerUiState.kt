package com.leoapps.mediapicker.picker.presentation.model

import com.leoapps.mediapicker.common.domain.model.Image

data class PickerUiState(
    val clickedItemId: Long = -1,
    val mediaItems: List<Image> = emptyList(),
) {
//    data class Image(
//        val id: Long,
//        val uri: Uri,
//        val isSelected: Boolean = false,
//    )
}
