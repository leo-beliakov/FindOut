package com.leoapps.findout.creation.form.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiAction

@Composable
internal fun TopBar(
    title: String,
    isContentScrolled: Boolean,
    onAction: (FormCreationUiAction) -> Unit
) {
    val elevation by animateDpAsState(
        targetValue = if (isContentScrolled) 8.dp else 0.dp,
        animationSpec = tween(),
        label = "",
    )

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .shadow(elevation)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Go back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .clickable(
                    onClick = { onAction(FormCreationUiAction.BackClicked) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 22.dp
                    )
                ),
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}