package com.leoapps.design_system.components.input.model

import androidx.annotation.StringRes

data class InputFieldState(
    val value: String,
    @StringRes val placeholderResId: Int,
    @StringRes val labelResId: Int,
)