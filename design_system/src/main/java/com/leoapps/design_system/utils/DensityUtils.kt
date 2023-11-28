package com.leoapps.design_system.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

@Stable
fun Dp.toPx(density: Density): Float = with(density) { this@toPx.toPx() }

@Composable
@Stable
fun Float.toDp(): Dp = with(LocalDensity.current) { this@toDp.toDp() }

@Composable
@Stable
fun Size.toDp(): DpSize = with(LocalDensity.current) { this@toDp.toDpSize() }