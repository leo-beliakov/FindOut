package com.leoapps.findout.add.presentation.composables

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.ui.theme.FindOutTheme
import com.leoapps.findout.ui.theme.Violet
import com.leoapps.findout.ui.theme.VioletLight

@Composable
fun AddImageSection(
    imageUri: Uri?,
    onAction: (AddUiAction) -> Unit
) {
    if (imageUri == null) {
        AddImagePlaceholder { onAction(AddUiAction.AddImageClicked) }
    } else {
        AddImagePlaceholder { onAction(AddUiAction.AddImageClicked) }
    }
}

@Composable
private fun AddImagePlaceholder(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = VioletLight)
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
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Violet
        )
    }
}

@Preview
@Composable
private fun AddImagePlaceholderPreview() {
    FindOutTheme {
        AddImagePlaceholder(
            onClick = {}
        )
    }
}