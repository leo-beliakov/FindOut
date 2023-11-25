package com.leoapps.mediapicker.picker.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.mediapicker.common.domain.repository.MediaRepository
import com.leoapps.mediapicker.picker.presentation.model.PickerUiAction
import com.leoapps.mediapicker.picker.presentation.model.PickerUiState
import com.leoapps.mediapicker.root.navigation.model.PickerNavCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PickerUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<PickerNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun onAction(action: PickerUiAction) {
        when (action) {
            is PickerUiAction.OnImageSelected -> {

            }

            PickerUiAction.OnGalleryPermissionGranted -> {
                viewModelScope.launch {
                    val results = mediaRepository.queryImages()
                    _state.update {
                        it.copy(
                            mediaItems = results
//                                .map { image ->
//                                PickerUiState.Image(
//                                    id = image.id,
//                                    uri = image.uri,
//                                )
//                            }
                        )
                    }
                }
            }

            is PickerUiAction.OnImageClicked -> {
                viewModelScope.launch {
                    _state.update { it.copy(clickedItemId = action.id) }
                }
            }

            PickerUiAction.OnCancelClicked -> {

            }
        }
    }
}