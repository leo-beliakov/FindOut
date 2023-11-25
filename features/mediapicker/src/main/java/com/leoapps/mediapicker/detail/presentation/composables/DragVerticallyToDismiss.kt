package com.leoapps.mediapicker.detail.presentation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.utils.toPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

private const val DRAG_THRESHOLD_DP = 100

fun Modifier.dragVerticallyToDismiss(
    state: DragVerticallyToDismissState
): Modifier {
    return this.pointerInput(Unit) {
        detectVerticalDragGestures(
            onDragEnd = { state.finishDrag() },
            onDragCancel = { state.finishDrag() },
            onVerticalDrag = { _, amount -> state.onVerticalDrag(amount) },
        )
    }
}

@Composable
fun rememberDragVerticallyToDismissState(
    onDismissed: () -> Unit
): DragVerticallyToDismissState {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val onDismissedState = rememberUpdatedState(onDismissed)

    return remember {
        DragVerticallyToDismissState(
            density = density,
            scope = coroutineScope,
            onDismissed = { onDismissedState.value.invoke() }
        )
    }
}

class DragVerticallyToDismissState(
    private val thresholdDp: Int = DRAG_THRESHOLD_DP,
    private val density: Density,
    private val scope: CoroutineScope,
    private val onDismissed: () -> Unit
) {
    val translationY = Animatable(0f)
    val dragProgress by derivedStateOf {
        val translationAbsolute = translationY.value.absoluteValue
        val translationNormalized = translationAbsolute / thresholdDp.dp.toPx(density)
        (translationNormalized).coerceAtMost(1f)
    }

    fun onVerticalDrag(dragAmount: Float) {
        scope.launch { translationY.snapTo(translationY.value + dragAmount) }
    }

    fun finishDrag() {
        if (dragProgress == 1f) {
            onDismissed()
        } else {
            scope.launch { translationY.animateTo(0f) }
        }
    }
}
