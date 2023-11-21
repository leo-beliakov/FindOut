package com.leoapps.mediapicker.root.presentation

import android.net.Uri
import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import com.leoapps.mediapicker.root.presentation.model.PickerRootUiState
import com.leoapps.mediapicker.root.presentation.model.TransitionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PickerRootViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PickerRootUiState())
    val state = _state.asStateFlow()

    fun openDetail(uri: Uri, startBounds: Rect) {
        _state.update {
            it.copy(
                isDetailShown = true,
                sharedElementUri = uri,
                sharedElementBounds = startBounds,
                sharedElementTransitionState = TransitionState.FORWARD,
            )
        }
    }

    fun onGoBackClickedDetail() {
        _state.update {
            it.copy(
                isDetailShown = false,
                sharedElementUri = Uri.EMPTY,
                sharedElementBounds = Rect.Zero,
                sharedElementTransitionState = TransitionState.NONE,
            )
        }
    }

    fun onBackClicked() {
        _state.update {
            it.copy(sharedElementTransitionState = TransitionState.BACKWARD)
        }
    }
}