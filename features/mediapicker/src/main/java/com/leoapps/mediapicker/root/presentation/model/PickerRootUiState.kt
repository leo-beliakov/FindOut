package com.leoapps.mediapicker.root.presentation.model

import android.net.Uri
import androidx.compose.ui.geometry.Rect

data class PickerRootUiState(
    val isDetailShown: Boolean = false,
    val sharedElementUri: Uri = Uri.EMPTY,
    val sharedElementBounds: Rect = Rect.Zero,
    val sharedElementTransitionState: TransitionState = TransitionState.NONE,
)