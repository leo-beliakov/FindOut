package com.leoapps.media_picker.domain.model

import android.net.Uri

data class Video(
    val uri: Uri,
    val name: String,
    val duration: Int,
    val size: Int
)