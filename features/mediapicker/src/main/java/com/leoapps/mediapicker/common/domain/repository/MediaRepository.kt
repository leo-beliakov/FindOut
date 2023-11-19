package com.leoapps.mediapicker.common.domain.repository

import com.leoapps.mediapicker.common.domain.model.Image

interface MediaRepository {
    suspend fun queryImages(): List<Image>
}