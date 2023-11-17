package com.leoapps.media_picker.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoapps.media_picker.domain.repository.MediaRepository
import com.leoapps.media_picker.presentation.model.PickerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
        Log.d("MyTag", "init")
        viewModelScope.launch {
            val results = mediaRepository.queryImages()
            Log.d("MyTag", "results = ${results.size}")
            _state.update {
                it.copy(
                    mediaItems = results.map { image ->
                        PickerUiState.Photo(
                            uri = image.uri,
                            selection = PickerUiState.SelectionState.Unselected,
                        )
                    }
                )
            }
        }
    }
}