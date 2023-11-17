package com.leoapps.media_picker.presentation.model

import android.net.Uri

data class PickerUiState(
    val allowMultipleSelection: Boolean = false,
    val mediaItems: List<Photo> = emptyList(),
) {
    data class Photo(
        val uri: Uri,
        val selection: SelectionState
    )

    sealed interface SelectionState {
        object Unselected : SelectionState

        data class Selected(
            val order: Int
        ) : SelectionState
    }
}
