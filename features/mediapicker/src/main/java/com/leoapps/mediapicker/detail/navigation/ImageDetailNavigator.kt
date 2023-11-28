package com.leoapps.mediapicker.detail.navigation

import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand

interface ImageDetailNavigator {
    fun onNavCommand(command: ImageDetailNavCommand)
}