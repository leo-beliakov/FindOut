package com.leoapps.media_picker.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import com.leoapps.media_picker.domain.model.MediaType

fun getMediaPermissions(mediaType: MediaType): Array<String> {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getMediaPermissionsApi33(mediaType)
        else -> getMediaPermissionsApiFallback()
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun getMediaPermissionsApi33(mediaType: MediaType): Array<String> {
    return when (mediaType) {
        MediaType.PHOTO_ONLY -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        MediaType.VIDEO_ONLY -> arrayOf(Manifest.permission.READ_MEDIA_VIDEO)
        MediaType.VIDEO_AND_PHOTO -> arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO
        )
    }
}

private fun getMediaPermissionsApiFallback(): Array<String> {
    return arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
}
