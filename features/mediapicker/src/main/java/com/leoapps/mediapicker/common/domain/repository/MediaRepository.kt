package com.leoapps.mediapicker.common.domain.repository

import android.net.Uri
import com.leoapps.mediapicker.common.domain.model.Image

interface MediaRepository {
    suspend fun queryImages(): List<Image>
    suspend fun copyImageToInternalStorage(image: Image): Uri?
}