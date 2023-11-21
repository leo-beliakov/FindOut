package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import com.leoapps.design_system.modifiers.clickableWithoutRipple
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState

@Composable
fun ImageItem(
    alpha: Float,
    image: PickerUiState.Image,
    onClick: (Rect) -> Unit
) {
    var rect by remember { mutableStateOf(Rect.Zero) }

    Image(
        painter = rememberAsyncImagePainter(image.uri),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .alpha(alpha)
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