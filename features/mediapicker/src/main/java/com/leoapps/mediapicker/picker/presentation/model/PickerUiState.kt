package com.leoapps.mediapicker.picker.presentation.model

import android.net.Uri

data class PickerUiState(
    val clickedItemIndex: Int = -1,
    val allowMultipleSelection: Boolean = false,
    val mediaItems: List<Photo> = emptyList(),
) {
    data class Photo(
        val id: Long,
        val uri: Uri,
        val isSelected: Boolean = false,
    )

    sealed interface SelectionState {
        object Unselected : SelectionState

        data class Selected(
            val order: Int
        ) : SelectionState
    }
}
