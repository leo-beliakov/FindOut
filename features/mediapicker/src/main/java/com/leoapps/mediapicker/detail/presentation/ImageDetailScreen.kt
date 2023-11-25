package com.leoapps.mediapicker.detail.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.modifiers.consumeAllInput
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.common.domain.model.Image
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

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .consumeAllInput()
    ) {
        val dragState = rememberDragVerticallyToDismissState(onBackClicked)
        val endBounds = remember { calculateEndBounds(image, constraints) }

        Scrim(
            dragProgress = dragState.dragProgress,
            sharedElementProgress = sharedElementProgress.value
        )

        SharedTransitionElement(
            image = image,
            startBounds = startBounds,
            endBounds = endBounds,
            sharedElementProgress = sharedElementProgress.value,
            dragState = dragState
        )
        TopBar(
            title = "7 of 2212",
            isElevated = true,
            onBackClicked = onBackClicked,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        BottomButton(
            text = stringResource(id = R.string.image_detail_button),
            onClick = onBackClicked, //todo pass the result
            modifier = Modifier.align(Alignment.BottomCenter)
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
    val scrimAlpha by remember {
        derivedStateOf { (1f - SCRIM_CHANGE_ON_DRAG * dragProgress) * sharedElementProgress }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(scrimAlpha)
            .background(Color.White)
    )
}
