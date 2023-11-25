package com.leoapps.design_system.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Consumes all input events, preventing them from being propagated further.
 */
fun Modifier.consumeAllInput(): Modifier {
    return this.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                awaitPointerEvent()
            }
        }
    }
}