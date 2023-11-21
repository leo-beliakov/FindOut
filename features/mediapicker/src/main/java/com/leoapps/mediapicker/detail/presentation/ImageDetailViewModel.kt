package com.leoapps.mediapicker.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand
import com.leoapps.mediapicker.detail.presentation.model.ImageDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ImageDetailViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ImageDetailUiState())
    val state = _state.asStateFlow()

    private val _navCommand = MutableSharedFlow<ImageDetailNavCommand>()
    val navCommand = _navCommand.asSharedFlow()

    fun onTransitionFinished() {
        viewModelScope.launch {
            _navCommand.emit(ImageDetailNavCommand.GoBack)
        }
    }
}