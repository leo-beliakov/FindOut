package com.leoapps.mediapicker.picker.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.common.utils.getImagesPermission
import com.leoapps.mediapicker.picker.presentation.composables.ImageItem
import com.leoapps.mediapicker.picker.presentation.composables.NoPermissionItem
import com.leoapps.mediapicker.picker.presentation.model.PickerUiAction
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState
import com.leoapps.mediapicker.root.navigation.PickerNavigator
import com.leoapps.mediapicker.root.presentation.model.TransitionState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PickerScreen(
    transitionState: TransitionState,
    navigator: PickerNavigator,
    viewModel: PickerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)
    val galleryPermission = rememberPermissionState(getImagesPermission()) { granted ->
        if (granted) {
            viewModel.onAction(PickerUiAction.OnGalleryPermissionGranted)
        }
    }

    PickerScreen(
        state = state,
        transitionState = transitionState,
        cameraPermission = cameraPermission,
        galleryPermission = galleryPermission,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        if (galleryPermission.status.isGranted) {
            viewModel.onAction(PickerUiAction.OnGalleryPermissionGranted)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PickerScreen(
    state: PickerUiState,
    transitionState: TransitionState,
    galleryPermission: PermissionState,
    cameraPermission: PermissionState,
    onAction: (PickerUiAction) -> Unit,
) {
    val gridState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(
                top = 6.dp,
                start = 6.dp,
                end = 6.dp,
                bottom = WindowInsets
                    .navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding() + 6.dp
            )
        ) {
            if (!cameraPermission.status.isGranted) {
                item {
                    NoPermissionItem(
                        titleResId = R.string.no_permission_item_camera,
                        iconResId = R.drawable.ic_perm_media,
                        onClick = { cameraPermission.launchPermissionRequest() }
                    )
                }
            }
            if (!galleryPermission.status.isGranted) {
                item {
                    NoPermissionItem(
                        titleResId = R.string.no_permission_item_gallery,
                        iconResId = R.drawable.ic_perm_media,
                        onClick = { galleryPermission.launchPermissionRequest() }
                    )
                }
            }
            if (galleryPermission.status.isGranted) {
                itemsIndexed(
                    items = state.mediaItems,
//                    key = { item -> item.id }
                ) { index, item ->
                    ImageItem(
                        alpha = if (state.clickedItemIndex == index && transitionState != TransitionState.NONE) 0.1f else 1f,
                        image = item,
                        onClick = { bounds ->
                            onAction(
                                PickerUiAction.OnImageClicked(
                                    index = index,
                                    uri = item.uri,
                                    elementBounds = bounds
                                )
                            )
                        }
                    )
                }
            }
        }
        BottomButton(
            text = "Cancel",
            onClick = { onAction(PickerUiAction.OnCancelClicked) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

