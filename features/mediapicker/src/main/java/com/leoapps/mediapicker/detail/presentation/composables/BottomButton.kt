package com.leoapps.mediapicker.detail.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.components.button.PrimaryButton

@Composable
fun BottomButton( //todo think about the naming!
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        PrimaryButton(
            text = text,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}