package com.leoapps.mediapicker.root.presentation.model

import androidx.compose.ui.geometry.Rect
import com.leoapps.mediapicker.common.domain.model.Image

data class PickerRootUiState(
    val isDetailShown: Boolean = false,
    val sharedElementImage: Image? = null,
    val sharedElementBounds: Rect = Rect.Zero,
    val sharedElementTransitionState: TransitionState = TransitionState.NONE,
)