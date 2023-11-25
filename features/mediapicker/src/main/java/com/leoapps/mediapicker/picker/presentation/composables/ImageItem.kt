package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.leoapps.design_system.modifiers.clickableWithoutRipple
import com.leoapps.mediapicker.common.domain.model.Image

@Composable
fun ImageItem(
    alpha: Float,
    image: Image,
    onClick: (Rect) -> Unit
) {
    var rect by remember { mutableStateOf(Rect.Zero) }

    AsyncImage(
        model = image.uri,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        alpha = alpha,
        modifier = Modifier
            .aspectRatio(1f)
            .clickableWithoutRipple { onClick(rect) }
            .onGloballyPositioned { coordinates ->
                rect = Rect(
                    offset = coordinates.positionInRoot(),
                    size = coordinates.size.toSize()
                )
            }
    )
}