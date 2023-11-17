package com.leoapps.media_picker.utils

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

fun getImagesPermissions(): Array<String> {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getImagesPermissionsApi33()
        else -> getImagesPermissionsApiFallback()
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun getImagesPermissionsApi33(): Array<String> {
    return arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
}

private fun getImagesPermissionsApiFallback(): Array<String> {
    return arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
}
