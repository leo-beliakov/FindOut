package com.leoapps.design_system.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Rect

@Stable
fun Rect.scale(scaleFactor: Float): Rect {
    val centerX = this.center.x
    val centerY = this.center.y

    val newHalfWidth = this.size.width * scaleFactor / 2
    val newHalfHeight = this.size.height * scaleFactor / 2

    return Rect(
        left = centerX - newHalfWidth,
        top = centerY - newHalfHeight,
        right = centerX + newHalfWidth,
        bottom = centerY + newHalfHeight
    )
}

@Stable
fun Rect.translateY(translateY: Float): Rect {
    return Rect(left, top + translateY, right, bottom + translateY)
}