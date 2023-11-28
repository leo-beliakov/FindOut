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
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import coil.compose.AsyncImage
import com.leoapps.design_system.modifiers.clickableWithoutRipple
import com.leoapps.mediapicker.common.domain.model.Image

@Composable
fun ImageItem(
    alpha: Float,
    image: Image,
    onClick: (Rect) -> Unit,
    modifier: Modifier = Modifier
) {
    var rect by remember { mutableStateOf(Rect.Zero) }

    AsyncImage(
        model = image.uri,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        alpha = alpha,
        modifier = modifier
            .aspectRatio(1f)
            .clickableWithoutRipple { onClick(rect) }
            .onGloballyPositioned { rect = it.boundsInRoot() }
    )
}