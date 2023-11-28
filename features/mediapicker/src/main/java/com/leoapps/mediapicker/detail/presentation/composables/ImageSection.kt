package com.leoapps.mediapicker.detail.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import coil.compose.AsyncImage
import com.leoapps.design_system.modifiers.offset
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.design_system.utils.scale
import com.leoapps.design_system.utils.toDp
import com.leoapps.design_system.utils.translateY
import com.leoapps.mediapicker.common.domain.model.Image


private const val SCALE_CHANGE_ON_DRAG = 0.2f

fun calculateEndBounds(
    image: Image,
    constraints: Constraints,
): Rect {
    val scaleX = constraints.maxWidth / image.width.toFloat()
    val scaleY = constraints.maxHeight / image.height.toFloat()
    val targetScale = minOf(scaleX, scaleY)

    val targetHeight = image.height * targetScale
    val targetWidth = image.width * targetScale

    val offsetX = (constraints.maxWidth - targetWidth) / 2
    val offsetY = (constraints.maxHeight - targetHeight) / 2

    return Rect(
        top = offsetY,
        bottom = constraints.maxHeight - offsetY,
        left = offsetX,
        right = constraints.maxWidth - offsetX
    )
}

@Composable
fun SharedTransitionElement(
    image: Image,
    startBounds: Rect,
    endBounds: Rect,
    sharedElementProgress: Float,
    dragState: DragVerticallyToDismissState
) {
    val scale by remember {
        derivedStateOf { 1f - SCALE_CHANGE_ON_DRAG * dragState.dragProgress }
    }

    val currentRect = lerp(
        fraction = sharedElementProgress,
        start = startBounds,
        stop = endBounds
            .translateY(dragState.translationY.value)
            .scale(scale),
    )

    AsyncImage(
        model = image.uri,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .offset(currentRect)
            .size(currentRect.size.toDp())
            .dragVerticallyToDismiss(dragState)
    )
}

@Preview
@Composable
private fun ImageSectionPreview() {
    FindOutTheme {
//        ImageSection(
//            uri = Uri.EMPTY,
//            modifier = Modifier.fillMaxSize(),
//            onDismiss = {},
//        )
    }
}

