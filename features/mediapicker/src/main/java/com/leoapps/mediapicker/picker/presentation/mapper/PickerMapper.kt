package com.leoapps.mediapicker.picker.presentation.mapper

import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState
import javax.inject.Inject

internal class PickerMapper @Inject constructor() {
    fun mapToUiState(
        images: List<Image>
    ): PickerUiState {
        if (images.isEmpty()) return PickerUiState()

        val albums = mapImagesToAlbums(images)
        val defaultAlbum = getDefaultAlbum(images)
        val allAlbums = albums.toMutableList()
        allAlbums.add(0, defaultAlbum)

        return PickerUiState(
            albums = allAlbums,
            allImages = images,
            selectedAlbum = defaultAlbum,
            selectedAlbumImages = images,
        )
    }

    private fun mapImagesToAlbums(images: List<Image>): List<PickerUiState.Album> {
        val groupedImages = images.groupBy { it.albumId }
        return groupedImages.keys.mapNotNull { albumId ->
            val imagesInAlbum = groupedImages[albumId] ?: return@mapNotNull null
            val firstImage = imagesInAlbum.first()
            PickerUiState.Album(
                id = albumId,
                name = firstImage.albumName,
                size = imagesInAlbum.size,
                coverImage = firstImage
            )
        }
    }

    private fun getDefaultAlbum(allImages: List<Image>): PickerUiState.Album {
        return PickerUiState.Album(
            id = PickerUiState.Album.DEFAULT_ALBUM_ID,
            name = "All", //todo resources
            size = allImages.size,
            coverImage = allImages.first()
        )
    }
}