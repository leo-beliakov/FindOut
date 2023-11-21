package com.leoapps.mediapicker.detail.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import com.leoapps.mediapicker.detail.presentation.composables.ImageSection
import com.leoapps.mediapicker.root.presentation.model.TransitionState

@Composable
fun ImageDetailScreen(
    startBounds: Rect,
    uri: Uri,
    transitionState: TransitionState,
    onBackClicked: () -> Unit,
    onTransitionFinished: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        ImageSection(
            startBounds = startBounds,
            uri = uri,
            transitionState = transitionState,
            onBackClicked = onBackClicked,
            onTransitionFinished = onTransitionFinished,
        )
    }
}
