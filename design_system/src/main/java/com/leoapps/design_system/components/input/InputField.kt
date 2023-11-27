package com.leoapps.design_system.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leoapps.design_system.components.input.model.InputFieldState
import com.leoapps.design_system.theme.VioletLight

@Composable
fun InputField(
    state: InputFieldState,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = state.labelResId),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = state.value,
            textStyle = MaterialTheme.typography.bodyLarge,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VioletLight,
                unfocusedBorderColor = VioletLight,
            ),
            placeholder = {
                Text(
                    text = stringResource(state.placeholderResId),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}