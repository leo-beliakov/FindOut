package com.leoapps.creation.form.presentation.composables

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.theme.Violet

internal fun LazyListScope.addImageSection(
    imageUri: Uri?,
    onClick: () -> Unit
) {
    if (imageUri == null) {
        item(key = "ImagePlaceholder") {
            ImagePlaceholder(onClick = onClick)
        }
    } else {
        item(key = "ImagePlaceholder") {
            ImagePlaceholder(onClick = onClick)
        }
    }
}

@Composable
private fun ImagePlaceholder(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 250.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = com.leoapps.design_system.theme.VioletLight)
            .clickable(onClick = onClick)

    ) {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            tint = Violet,
            modifier = Modifier.size(72.dp)
        )
        Text(
            text = "Add Cover Image",
            style = MaterialTheme.typography.titleLarge,
            color = Violet
        )
    }
}

@Preview
@Composable
private fun AddImagePlaceholderPreview() {
    com.leoapps.design_system.theme.FindOutTheme {
        ImagePlaceholder(
            onClick = {}
        )
    }
}