package com.leoapps.mediapicker.detail.presentation

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.presentation.composables.ImageSection
import com.leoapps.mediapicker.root.presentation.model.TransitionState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ImageDetailScreen(
    navigator: ImageDetailNavigator,
    startBounds: Rect,
    uri: Uri,
    transitionState: TransitionState,
    onBackClicked: () -> Unit,
    viewModel: ImageDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ImageDetailScreen(
        uri = uri,
        transitionState = transitionState,
        startBounds = startBounds,
        onBackClicked = onBackClicked,
        onTransitionFinished = {
            if (transitionState == TransitionState.BACKWARD) {
                viewModel.onTransitionFinished()
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}

@Composable
private fun ImageDetailScreen(
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
