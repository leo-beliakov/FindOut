package com.leoapps.mediapicker.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.presentation.composables.ImageSection
import com.leoapps.mediapicker.detail.presentation.model.ImageDetailUiState
import com.leoapps.mediapicker.root.navigation.PickerNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@PickerNavGraph
@Destination(navArgsDelegate = ImageDetailArgs::class)
@Composable
internal fun ImageDetailScreen(
    navigator: ImageDetailNavigator,
    viewModel: ImageDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ImageDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}

@Composable
private fun ImageDetailScreen(
    state: ImageDetailUiState,
    onAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        ImageSection(
            uri = state.uri,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        )
        Button(
            onClick = { onAction() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.image_detail_button)
            )
        }
    }
}
