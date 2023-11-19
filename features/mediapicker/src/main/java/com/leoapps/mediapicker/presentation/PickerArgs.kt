package com.leoapps.mediapicker.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PickerArgs(
    val type: String = ""
) : Parcelable