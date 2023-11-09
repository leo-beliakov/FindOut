package com.leoapps.findout.add.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.ui.theme.Violet
import com.leoapps.findout.ui.theme.VioletLight


fun LazyListScope.addTitleSection(
    title: String,
    description: String,
    hasDescription: Boolean,
    onAction: (AddUiAction) -> Unit
) {
    item(key = "TitleSection") {
        Column {
            Text(
                text = "Title",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            OutlinedTextField(
                value = title,
                textStyle = MaterialTheme.typography.bodyLarge,
                onValueChange = { newValue -> onAction(AddUiAction.TitleUpdated(newValue)) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VioletLight,
                    unfocusedBorderColor = VioletLight,
                ),
                placeholder = {
                    Text(
                        text = "Enter survey title"
                    )
                },
            )
            AnimatedContent(
                targetState = hasDescription,
                label = "Add description animation"
            ) { hasDesc ->
                if (hasDesc) {
                    AddDescriptionField(
                        description = description,
                        onChange = { newValue -> onAction(AddUiAction.DescriptionUpdated(newValue)) }
                    )
                } else {
                    AddDescriptionButton(
                        onClick = { onAction(AddUiAction.AddDescriptionClicked) }
                    )
                }
            }
        }
    }
}

@Composable
fun AddDescriptionButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = Violet
        )
        Text(
            text = "Add Description",
            style = MaterialTheme.typography.labelLarge,
            color = Violet
        )
    }
}

@Composable
fun AddDescriptionField(
    description: String,
    onChange: (String) -> Unit
) {
    Column {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = description,
            textStyle = MaterialTheme.typography.bodyLarge,
            onValueChange = onChange,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VioletLight,
                unfocusedBorderColor = VioletLight,
            ),
            placeholder = {
                Text(
                    text = "Enter survey description"
                )
            }
        )
    }
}
