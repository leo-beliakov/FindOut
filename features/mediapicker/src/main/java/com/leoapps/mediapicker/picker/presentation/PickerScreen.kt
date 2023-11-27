package com.leoapps.mediapicker.picker.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.leoapps.design_system.theme.GrayUltraLight2
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.common.presentation.composables.BottomButton
import com.leoapps.mediapicker.common.utils.getImagesPermission
import com.leoapps.mediapicker.picker.presentation.composables.ImageItem
import com.leoapps.mediapicker.picker.presentation.composables.NoPermissionAlert
import com.leoapps.mediapicker.picker.presentation.composables.TopBar
import com.leoapps.mediapicker.picker.presentation.composables.cameraSection
import com.leoapps.mediapicker.picker.presentation.model.PickerUiAction
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState
import com.leoapps.mediapicker.root.presentation.model.TransitionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun PickerScreen(
    transitionState: TransitionState,
    onCancelClicked: () -> Unit,
    onImageClick: (Rect, Image) -> Unit,
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
        onAlbumSelected = {
            viewModel.onAction(
                PickerUiAction.OnAlbumSelected(it)
            )
        },
        onCancelClicked = onCancelClicked,
        onImageClick = { bounds, image ->
            viewModel.onAction(PickerUiAction.OnImageClicked(image.id))
            onImageClick(bounds, image)
        },
    )

    LaunchedEffect(Unit) {
        if (galleryPermission.status.isGranted) {
            viewModel.onAction(PickerUiAction.OnGalleryPermissionGranted)
        } else {
            galleryPermission.launchPermissionRequest()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalFoundationApi::class)
@Composable
private fun PickerScreen(
    state: PickerUiState,
    transitionState: TransitionState,
    galleryPermission: PermissionState,
    cameraPermission: PermissionState,
    onAlbumSelected: (PickerUiState.Album) -> Unit,
    onCancelClicked: () -> Unit,
    onImageClick: (Rect, Image) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayUltraLight2)
    ) {
        TopBar(
            selectedAlbum = state.selectedAlbum,
            albums = state.albums,
            onAlbumSelected = onAlbumSelected,
        )
        if (!galleryPermission.status.isGranted) {
            NoPermissionAlert(
                onClick = {}
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier.weight(1f, true)
            ) {
                cameraSection(
                    onClick = { }
                )
                items(
                    items = state.selectedAlbumImages,
                    key = { item -> item.id }
                ) { item ->
                    val shouldAlterAlpha = state.clickedItemId == item.id &&
                            transitionState != TransitionState.NONE

                    ImageItem(
                        image = item,
                        alpha = if (shouldAlterAlpha) 0.1f else 1f,
                        onClick = { bounds -> onImageClick(bounds, item) },
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }
        }
        BottomButton(
            text = stringResource(id = R.string.picker_cancel_button),
            onClick = onCancelClicked
        )
    }
}