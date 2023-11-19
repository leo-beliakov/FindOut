package com.leoapps.mediapicker.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.leoapps.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.theme.BlackOpacity25
import com.leoapps.design_system.theme.Purple40
import com.leoapps.mediapicker.R
import com.leoapps.mediapicker.navigation.PickerNavGraph
import com.leoapps.mediapicker.presentation.composables.NoPermissionItem
import com.leoapps.mediapicker.presentation.model.PickerUiAction
import com.leoapps.mediapicker.presentation.model.PickerUiState
import com.leoapps.mediapicker.utils.getImagesPermission
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

@OptIn(ExperimentalPermissionsApi::class)
@PickerNavGraph(start = true)
@Destination(
    style = DestinationStyleBottomSheet::class,
    navArgsDelegate = PickerArgs::class
)
@Composable
fun PickerScreen(
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
        cameraPermission = cameraPermission,
        galleryPermission = galleryPermission,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        if (galleryPermission.status.isGranted) {
            viewModel.onAction(PickerUiAction.OnGalleryPermissionGranted)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PickerScreen(
    state: PickerUiState,
    galleryPermission: PermissionState,
    cameraPermission: PermissionState,
    onAction: (PickerUiAction) -> Unit,
) {
    val gridState = rememberLazyGridState()


    Box(
        modifier = Modifier.wrapContentHeight()
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
                bottom = BOTTOM_GRADIENT_HEIGHT_DP.dp
//                WindowInsets
//                    .navigationBars
//                    .asPaddingValues()
//                    .calculateBottomPadding() + 6.dp
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
                items(
                    items = state.mediaItems,
//                    key = { item -> item.id }
                ) { item ->
                    ImageItem(
                        image = item,
                        isSelected = item.isSelected,
                        onAction = onAction
                    )
                }
            }
        }
        BottomButton(
            text = "Freacvweve",
            onClick = { onAction(PickerUiAction.OnCancelClicked) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ImageItem(
    image: PickerUiState.Photo,
    isSelected: Boolean,
    onAction: (PickerUiAction) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onAction(PickerUiAction.OnImageClicked(image.id)) },
    ) {
        Image(
            painter = rememberAsyncImagePainter(image.uri),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .aspectRatio(1f),
        )
        SelectionIndicator(
            isSelected = isSelected,
            onClick = { Log.d("MyTag", "selection") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
        )
    }
}

@Composable
private fun SelectionIndicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Int,
) {
    if (isSelected) {
        Canvas(modifier = modifier.size(22.dp)) {
            drawCircle(
                color = Purple40,
                style = Fill
            )
//            drawCircle(
//                color = Color.White,
//                style = Stroke(width = 2.dp.toPx())
//            )
        }
    } else {
        Canvas(modifier = modifier.size(22.dp)) {
            drawCircle(
                color = BlackOpacity25,
                style = Fill
            )
            drawCircle(
                color = Color.White,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}


