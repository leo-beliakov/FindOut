package com.leoapps.media_picker.presentation.model

import android.net.Uri

data class PickerUiState(
    val allowMultipleSelection: Boolean = false,
    val mediaItems: List<Item> = emptyList(),
) {
    sealed class Item(
        open val uri: Uri,
        open val selection: SelectionState
    ) {
        data class Video(
            val durationText: String,
            override val uri: Uri,
            override val selection: SelectionState
        ) : Item(uri, selection)

        data class Photo(
            override val uri: Uri,
            override val selection: SelectionState
        ) : Item(uri, selection)
    }

    sealed interface SelectionState {
        object Unselected : SelectionState

        data class Selected(
            val order: Int
        ) : SelectionState
    }
}
