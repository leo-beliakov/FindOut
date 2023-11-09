package com.leoapps.findout.add.presentation.model

import android.net.Uri

data class AddUiState(
    val pageName: String = "Create Survey",
    val title: String = "",
    val description: String? = null,
    val coverUri: Uri? = null,
    val questions: List<Any> = emptyList(),
)