package com.leoapps.mediapicker.domain.repository

import com.leoapps.mediapicker.domain.model.Image

interface MediaRepository {
    suspend fun queryImages(): List<Image>
}