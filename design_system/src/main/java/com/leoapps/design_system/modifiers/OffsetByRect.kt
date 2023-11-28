package com.leoapps.design_system.modifiers

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import com.leoapps.design_system.utils.toDp


@Stable
fun Modifier.offset(rect: Rect) = composed {
    this.offset(
        rect.left.toDp(),
        rect.top.toDp(),
    )
}