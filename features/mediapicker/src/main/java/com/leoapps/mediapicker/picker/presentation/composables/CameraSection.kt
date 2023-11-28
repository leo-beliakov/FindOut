package com.leoapps.mediapicker.picker.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.theme.Purple40


fun LazyGridScope.cameraSection(onClick: () -> Unit) {
    item(span = { GridItemSpan(3) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.PhotoCamera,
                tint = Purple40,
                contentDescription = "null"
            )
            Text(
                text = "Photo",
                style = MaterialTheme.typography.bodyLarge,
                color = Purple40,
            )
        }
    }
}