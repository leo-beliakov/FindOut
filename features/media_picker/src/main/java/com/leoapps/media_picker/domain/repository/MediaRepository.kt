package com.leoapps.media_picker.domain.repository

import com.leoapps.media_picker.domain.model.Photo
import com.leoapps.media_picker.domain.model.Video

interface MediaRepository {
    fun queryPhotos(): List<Photo>
    fun queryVideos(): List<Video>
}