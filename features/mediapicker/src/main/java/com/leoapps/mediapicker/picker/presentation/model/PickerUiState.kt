package com.leoapps.mediapicker.picker.presentation.model

import com.leoapps.mediapicker.common.domain.model.Image

data class PickerUiState(
    val clickedItemId: Long = -1,
    val selectedAlbum: Album? = null,
    val allImages: List<Image> = emptyList(),
    val selectedAlbumImages: List<Image> = emptyList(),
    val albums: List<Album> = emptyList(),
) {

    data class Album(
        val id: Long,
        val name: String,
        val size: Int,
        val coverImage: Image
    ) {
        companion object {
            const val DEFAULT_ALBUM_ID = -1L
        }
    }
}
