package com.leoapps.media_picker.domain.model

import android.net.Uri

data class Photo(
    val uri: Uri,
    val name: String,
    val size: Int
)