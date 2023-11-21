package com.leoapps.mediapicker.detail.presentation.composables

import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.leoapps.design_system.modifiers.offset
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.design_system.utils.scale
import com.leoapps.design_system.utils.toDp
import com.leoapps.design_system.utils.toPx
import com.leoapps.design_system.utils.translateY
import com.leoapps.mediapicker.root.presentation.model.TransitionState
import kotlin.math.absoluteValue

private const val DRAG_THRESHOLD = 0.45f

@Composable
fun BoxWithConstraintsScope.calculateEndBounds(startBounds: Rect): Rect {
    val density = LocalDensity.current
    val initialSize = startBounds.size.toDp()
    val aspectRatio = initialSize.width / initialSize.height
    val targetHeight = maxWidth / aspectRatio

    return Rect(
        top = ((maxHeight - targetHeight) / 2).toPx(density),
        bottom = ((maxHeight - targetHeight) / 2 + targetHeight).toPx(density),
        left = 0f,
        right = maxWidth.toPx(density)
    )
}

@Composable
internal fun ImageSection(
    startBounds: Rect,
    uri: Uri,
    transitionState: TransitionState,
    onBackClicked: () -> Unit,
    onTransitionFinished: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent()
                    }
                }
            }
    ) {
        var sharedElementProgress by remember { mutableStateOf(0f) }
        var dragProgress by remember { mutableStateOf(0f) }
        val scrimAlpha by remember { derivedStateOf { (1f - 0.5f * dragProgress) * sharedElementProgress } }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(scrimAlpha)
                .background(Color.Black)
        )
        SharedTransitionElement(
            startBounds = startBounds,
            endBounds = calculateEndBounds(startBounds),
            uri = uri,
            transitionState = transitionState,
            onDragProgressChange = { dragProgress = it },
            onSharedElementProgressChange = { sharedElementProgress = it },
            onDismissed = onBackClicked,
            onTransitionFinished = onTransitionFinished,
        )
    }
}


@Composable
fun SharedTransitionElement(
    startBounds: Rect,
    endBounds: Rect,
    uri: Uri,
    transitionState: TransitionState,
    onDragProgressChange: (Float) -> Unit,
    onSharedElementProgressChange: (Float) -> Unit,
    onDismissed: () -> Unit,
    onTransitionFinished: () -> Unit,
) {
    val sharedElementProgress = remember {
        Animatable(if (transitionState == TransitionState.FORWARD) 0f else 1f)
    }

    var translationY by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(0f) }

    val currentRect = lerp(
        start = startBounds,
        stop = endBounds.translateY(translationY).scale(scale),
        fraction = sharedElementProgress.value
    )


    DraggableElement(
        uri = uri,
        onDragProgressChange = onDragProgressChange,
        onTranslationAndScaleChange = { translation, s ->
            translationY = translation
            scale = s
        },
        onDismissed = onDismissed,
        modifier = Modifier
            .offset(currentRect)
            .size(currentRect.size.toDp())
            .drawBehind { }
    )

    LaunchedEffect(transitionState) {
        if (transitionState != TransitionState.NONE) {
            sharedElementProgress.animateTo(
                targetValue = if (transitionState == TransitionState.FORWARD) 1f else 0f,
                animationSpec = tween(300)
            )
            onTransitionFinished()
        }
    }

    LaunchedEffect(sharedElementProgress.value) {
        onSharedElementProgressChange(sharedElementProgress.value)
    }
}


@Composable
fun DraggableElement(
    uri: Uri,
    onDismissed: () -> Unit,
    onDragProgressChange: (Float) -> Unit,
    onTranslationAndScaleChange: (Float, Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    var animateToDefault by remember { mutableStateOf(false) }

    var translation by remember { mutableFloatStateOf(0f) }
    var dragProgress by remember { mutableFloatStateOf(0f) }

    val transition = updateTransition(targetState = animateToDefault, label = "transition")
    val animatedProgress by transition.animateFloat(label = "progress") { if (it) 0f else dragProgress }
    val animatedTranslation by transition.animateFloat(label = "translation") { if (it) 0f else translation }
    val scale by remember { derivedStateOf { (1f - 0.2f * animatedProgress) } }

    Image(
        painter = rememberAsyncImagePainter(model = uri),
        contentScale = ContentScale.FillWidth,
        contentDescription = "",
        modifier = modifier
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = {
                        animateToDefault = false
                    },
                    onDragEnd = {
                        if (dragProgress == 1f) {
                            onDismissed()
                        } else {
                            animateToDefault = true
                            translation = 0f
                            dragProgress = 0f
                        }
                    },
                    onDragCancel = {
                        if (dragProgress == 1f) {
                            onDismissed()
                        } else {
                            animateToDefault = true
                            translation = 0f
                            dragProgress = 0f
                        }
                    },
                    onVerticalDrag = { change, dragAmount ->
                        translation += dragAmount
                        dragProgress =
                            ((translation.absoluteValue / size.height) / DRAG_THRESHOLD).coerceAtMost(
                                1f
                            )
                    },
                )
            }
    )

    LaunchedEffect(animatedProgress) {
        onDragProgressChange(animatedProgress)
    }
    LaunchedEffect(animatedTranslation) {
        onTranslationAndScaleChange(animatedTranslation, scale)
    }
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

