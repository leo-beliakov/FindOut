package com.leoapps.mediapicker.root.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.mediapicker.detail.presentation.ImageDetailScreen
import com.leoapps.mediapicker.picker.presentation.PickerScreen
import com.leoapps.mediapicker.root.navigation.MediaPickerTransitions
import com.leoapps.mediapicker.root.navigation.PickerNavGraph
import com.leoapps.mediapicker.root.navigation.PickerNavigator
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@PickerNavGraph(start = true)
@Destination(style = MediaPickerTransitions::class)
@Composable
fun PickerRootScreen(
    navigator: PickerNavigator,
    viewModel: PickerRootViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PickerScreen(
            transitionState = state.sharedElementTransitionState,
            onCancelClicked = viewModel::onBackClicked,
            onImageClick = viewModel::openDetail,
        )
        if (state.isDetailShown) {
            ImageDetailScreen(
                image = state.sharedElementImage!!, //todo it's ugly
                startBounds = state.sharedElementBounds,
                transitionState = state.sharedElementTransitionState,
                onBackClicked = viewModel::onDismissedDetailScreen,
                onTransitionFinished = viewModel::onTransitionFinished,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}