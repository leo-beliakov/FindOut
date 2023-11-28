package com.leoapps.design_system.components.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leoapps.design_system.theme.GrayLight
import com.leoapps.design_system.theme.GrayUltraLight
import com.leoapps.design_system.theme.Purple40

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40,
            disabledContainerColor = GrayUltraLight,
            disabledContentColor = GrayLight
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }
}