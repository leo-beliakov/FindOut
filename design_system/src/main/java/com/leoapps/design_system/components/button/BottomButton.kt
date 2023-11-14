package com.leoapps.design_system.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.theme.GrayLight
import com.leoapps.design_system.theme.GrayUltraLight

const val BOTTOM_GRADIENT_HEIGHT_DP = 140

@Composable
fun BottomButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(BOTTOM_GRADIENT_HEIGHT_DP.dp)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.2f to Color.Transparent,
                        1f to Color.White
                    )
                )
            )
            .padding(
                bottom = 32.dp,
                start = 32.dp,
                end = 32.dp
            )
    ) {
        Button(
            enabled = enabled,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = GrayUltraLight,
                disabledContentColor = GrayLight
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Text(text = text)
        }
    }
}