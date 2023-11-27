package com.leoapps.mediapicker.detail.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.stringResource
import com.leoapps.design_system.modifiers.consumeAllInput
import com.leoapps.design_system.theme.GrayUltraLight2
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.common.presentation.composables.BottomButton
import com.leoapps.mediapicker.detail.presentation.composables.SharedTransitionElement
import com.leoapps.mediapicker.detail.presentation.composables.TopBar
import com.leoapps.mediapicker.detail.presentation.composables.calculateEndBounds
import com.leoapps.mediapicker.detail.presentation.composables.rememberDragVerticallyToDismissState
import com.leoapps.mediapicker.root.presentation.model.TransitionState

private const val SCRIM_CHANGE_ON_DRAG = 0.5f

@Composable
fun ImageDetailScreen(
    startBounds: Rect,
    image: Image,
    transitionState: TransitionState,
    onBackClicked: () -> Unit,
    onTransitionFinished: () -> Unit,
) {
    val sharedElementProgress = remember {
        Animatable(if (transitionState == TransitionState.FORWARD) 0f else 1f)
    }
    var sharedElementContainerCoordinates by remember {
        mutableStateOf<Offset?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .consumeAllInput()
    ) {
        TopBar(
            title = "7 of 2212",
            onBackClicked = onBackClicked,
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .onGloballyPositioned { coordinates ->
                    sharedElementContainerCoordinates = coordinates.positionInRoot()
                }
        ) {
            val dragState = rememberDragVerticallyToDismissState(onBackClicked)
            val endBounds = remember { calculateEndBounds(image, constraints) }

            Scrim(
                dragProgress = dragState.dragProgress,
                sharedElementProgress = sharedElementProgress.value
            )
            sharedElementContainerCoordinates?.let {
                SharedTransitionElement(
                    image = image,
                    startBounds = startBounds.translate(it.unaryMinus()),
                    endBounds = endBounds,
                    sharedElementProgress = sharedElementProgress.value,
                    dragState = dragState
                )
            }
        }
        BottomButton(
            text = stringResource(id = R.string.image_detail_button),
            onClick = onBackClicked, //todo pass the result
        )
    }

    LaunchedEffect(transitionState) {
        sharedElementProgress.animateTo(
            targetValue = if (transitionState == TransitionState.FORWARD) 1f else 0f,
            animationSpec = tween(300)
        )
        onTransitionFinished()
    }
}

@Composable
fun Scrim(
    dragProgress: Float,
    sharedElementProgress: Float
) {
    val scrimAlpha = remember(dragProgress, sharedElementProgress) {
        (1f - SCRIM_CHANGE_ON_DRAG * dragProgress) * sharedElementProgress
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(scrimAlpha)
            .background(GrayUltraLight2)
    )
}
