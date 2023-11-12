package com.leoapps.findout.creation.form.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.components.input.InputField
import com.leoapps.design_system.components.input.model.InputFieldState

internal fun LazyListScope.titleSection(
    titleState: InputFieldState,
    descriptionState: InputFieldState,
    hasDescription: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddDescriptionClick: () -> Unit,
) {
    item(key = "TitleSection") {
        Column {
            InputField(
                state = titleState,
                onValueChange = onTitleChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
            )
            AnimatedContent(
                targetState = hasDescription,
                label = "Add description animation"
            ) { hasDesc ->
                if (hasDesc) {
                    InputField(
                        state = descriptionState,
                        onValueChange = onDescriptionChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                    )
                } else {
                    AddDescriptionButton(
                        onClick = onAddDescriptionClick
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
            tint = com.leoapps.design_system.theme.Violet
        )
        Text(
            text = "Add Description",
            style = MaterialTheme.typography.labelLarge,
            color = com.leoapps.design_system.theme.Violet
        )
    }
}
