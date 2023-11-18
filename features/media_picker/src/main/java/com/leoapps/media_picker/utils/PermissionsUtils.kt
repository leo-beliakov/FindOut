package com.leoapps.media_picker.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

fun getImagesPermission(): String {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getImagesPermissionsApi33()
        else -> getImagesPermissionsApiFallback()
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun getImagesPermissionsApi33() = Manifest.permission.READ_MEDIA_IMAGES

private fun getImagesPermissionsApiFallback() = Manifest.permission.READ_EXTERNAL_STORAGE
