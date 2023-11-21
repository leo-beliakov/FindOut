package com.leoapps.mediapicker.root.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand
import com.leoapps.mediapicker.detail.presentation.ImageDetailScreen
import com.leoapps.mediapicker.picker.presentation.PickerScreen
import com.leoapps.mediapicker.root.navigation.PickerNavGraph
import com.leoapps.mediapicker.root.navigation.PickerNavigator
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import com.ramcosta.composedestinations.annotation.Destination

@PickerNavGraph(start = true)
@Destination
@Composable
fun PickerRootScreen(
    viewModel: PickerRootViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PickerScreen(
            transitionState = state.sharedElementTransitionState,
            navigator = object : PickerNavigator {
                override fun onNavCommand(command: PickerNavCommand) {
                    when (command) {
                        PickerNavCommand.GoBack -> {}
                        is PickerNavCommand.OpenImageDetail -> {
                            viewModel.openDetail(command.uri, command.startBounds)
                        }
                    }
                }
            }
        )
        if (state.isDetailShown) {
            ImageDetailScreen(
                navigator = object : ImageDetailNavigator {
                    override fun onNavCommand(command: ImageDetailNavCommand) {
                        when (command) {
                            ImageDetailNavCommand.GoBack -> viewModel.onGoBackClickedDetail()
                        }
                    }
                },
                uri = state.sharedElementUri,
                startBounds = state.sharedElementBounds,
                onBackClicked = viewModel::onBackClicked,
                transitionState = state.sharedElementTransitionState,
            )
        }
    }

}