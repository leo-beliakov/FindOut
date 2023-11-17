package com.leoapps.media_picker.domain.repository

import com.leoapps.media_picker.domain.model.Image

interface MediaRepository {
    suspend fun queryImages(): List<Image>
}