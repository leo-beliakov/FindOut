package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState

@Composable
fun ImageItem(
    alpha: Float,
    image: PickerUiState.Photo,
    onClick: (Rect) -> Unit
) {
    var offset by remember { mutableStateOf(Offset.Unspecified) }
    var size by remember { mutableStateOf(Size.Zero) }

    Image(
        painter = rememberAsyncImagePainter(image.uri),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .alpha(alpha)
            .height(200.dp)
            .aspectRatio(1f)
            .clickable { onClick(Rect(offset, size)) }
            .onGloballyPositioned { coordinates ->
                offset = coordinates.positionInRoot()
                size = coordinates.size.toSize()
            }
    )
}