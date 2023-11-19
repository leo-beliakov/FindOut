package com.leoapps.mediapicker.detail.navigation.model

sealed interface ImageDetailNavCommand {
    object GoBack : ImageDetailNavCommand
}