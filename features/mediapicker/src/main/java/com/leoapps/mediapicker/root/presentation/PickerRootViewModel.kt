package com.leoapps.mediapicker.root.presentation

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.mediapicker.common.domain.model.Image
import com.leoapps.mediapicker.common.domain.repository.MediaRepository
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import com.leoapps.mediapicker.root.presentation.model.PickerRootUiState
import com.leoapps.mediapicker.root.presentation.model.TransitionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickerRootViewModel @Inject constructor(
    private val repository: MediaRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(PickerRootUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<PickerNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun openDetail(startBounds: Rect, image: Image) {
        _state.update {
            it.copy(
                isDetailShown = true,
                sharedElementImage = image,
                sharedElementBounds = startBounds,
                sharedElementTransitionState = TransitionState.FORWARD,
            )
        }
    }

    fun onDismissedDetailScreen() {
        _state.update {
            it.copy(sharedElementTransitionState = TransitionState.BACKWARD)
        }
    }

    fun onTransitionFinished() {
        if (state.value.sharedElementTransitionState == TransitionState.BACKWARD) {
            _state.update {
                it.copy(
                    isDetailShown = false,
                    sharedElementImage = null,
                    sharedElementBounds = Rect.Zero,
                    sharedElementTransitionState = TransitionState.NONE,
                )
            }
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _navCommand.emit(
                PickerNavCommand.GoBack
            )
        }
    }

    fun onImageSelected(image: Image) {
        viewModelScope.launch {
            val internalStorageUri = repository.copyImageToInternalStorage(image) ?: return@launch
            _navCommand.emit(PickerNavCommand.GoBackWithResult(internalStorageUri))
        }
    }
}