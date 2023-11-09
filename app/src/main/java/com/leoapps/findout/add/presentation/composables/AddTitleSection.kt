package com.leoapps.findout.add.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.ui.theme.GrayLight
import com.leoapps.findout.ui.theme.Violet
import com.leoapps.findout.ui.theme.VioletLight

@Composable
fun AddTitleSection(
    title: String,
    description: String?,
    onAction: (AddUiAction) -> Unit
) {
    Column {
        Text(
            text = "Title",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = title,
            onValueChange = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VioletLight,
                unfocusedBorderColor = VioletLight,
                unfocusedTextColor = GrayLight,
                focusedTextColor = GrayLight
            ),
            placeholder = {
                Text(
                    text = "Enter survey title"
                )
            },
        )
        AnimatedContent(
            targetState = description,
            label = "Add description animation"
        ) { desc ->
            if (desc.isNullOrEmpty()) {
                AddDescriptionButton(
                    onClick = {}
                )
            } else {
                AddDescriptionField(
                    description = desc,
                    onDescriptionChange = {}
                )
            }
        }
    }
}

@Composable
fun AddDescriptionButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = Violet
        )
        Text(
            text = "Add description",
            color = Violet
        )
    }
}

@Composable
fun AddDescriptionField(
    description: String,
    onDescriptionChange: () -> Unit
) {
    Column {
        Text(
            text = "Description",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = description,
            onValueChange = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VioletLight,
                unfocusedBorderColor = VioletLight,
                unfocusedTextColor = GrayLight,
                focusedTextColor = GrayLight
            ),
            placeholder = {
                Text(
                    text = "Enter survey description"
                )
            }
        )
    }
}
