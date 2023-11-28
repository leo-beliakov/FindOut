package com.leoapps.mediapicker.common.domain.model

import android.net.Uri

data class Image(
    val id: Long,
    val uri: Uri,
    val width: Int,
    val height: Int,
    val albumId: Long,
    val albumName: String,
)