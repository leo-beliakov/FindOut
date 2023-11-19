package com.leoapps.mediapicker.detail.presentation.composables

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.leoapps.design_system.theme.FindOutTheme
import kotlin.math.absoluteValue

private const val DRAG_THRESHOLD = 0.45f

@Composable
internal fun ImageSection(
    uri: Uri,
    modifier: Modifier = Modifier
) {
    var animateToDefault by remember { mutableStateOf(false) }

    var translation by remember { mutableFloatStateOf(0f) }
    var dragProgress by remember { mutableFloatStateOf(0f) }

    val transition = updateTransition(targetState = animateToDefault, label = "transition")
    val animatedProgress by transition.animateFloat(label = "progress") { if (it) 0f else dragProgress }
    val animatedTranslation by transition.animateFloat(label = "translation") { if (it) 0f else translation }

    val scale by remember { derivedStateOf { (1f - animatedProgress).coerceAtLeast(0.8f) } }
    val scrimAlpha by remember { derivedStateOf { (1f - animatedProgress).coerceAtLeast(0.5f) } }

    Box(
        modifier = modifier
            .drawBehind {
                drawRect(
                    color = Color.White,
                    alpha = scrimAlpha
                )
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(scale)
                .graphicsLayer { translationY = animatedTranslation }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragStart = {
                            animateToDefault = false
                            Log.d("MyTag", "size = ${size}")
                        },
                        onDragEnd = {
                            animateToDefault = true
                            translation = 0f
                            dragProgress = 0f
                            Log.d("MyTag", "size = ${size}")
                        },
                        onDragCancel = {
                            animateToDefault = true
                            translation = 0f
                            dragProgress = 0f
                            Log.d("MyTag", "onDragCancel")
                        },
                        onVerticalDrag = { change, dragAmount ->
                            translation += dragAmount
                            dragProgress =
                                ((translation.absoluteValue / size.height) / DRAG_THRESHOLD).coerceAtMost(
                                    1f
                                )
                            Log.d(
                                "MyTag",
                                "dragAmount = ${dragAmount} trans = $translation progress = $dragProgress"
                            )
                        },
                    )
                }
        )
    }
}


@Preview
@Composable
private fun ImageSectionPreview() {
    FindOutTheme {
        ImageSection(
            uri = Uri.EMPTY,
            modifier = Modifier.fillMaxSize()
        )
    }
}