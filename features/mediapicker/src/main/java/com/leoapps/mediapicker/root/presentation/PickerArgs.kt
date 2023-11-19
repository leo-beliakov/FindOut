package com.leoapps.mediapicker.root.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PickerArgs(
    val type: String = ""
) : Parcelable