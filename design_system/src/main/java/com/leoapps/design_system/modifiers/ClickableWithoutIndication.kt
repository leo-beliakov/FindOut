package com.leoapps.design_system.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

@Composable
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickableWithoutRipple"
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        onClick = onClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}