package com.leoapps.findout.add.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoapps.findout.add.presentation.model.AddUiState
import com.leoapps.findout.ui.theme.FindOutTheme

@Composable
fun AddQuestionItem(
    question: AddUiState.Question
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(12.dp)
    ) {
        Text(
            text = question.title,
            modifier = Modifier.weight(1f, true)
        )
        Icon(
            imageVector = Icons.Filled.Reorder,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun AddQuestionItemPreview() {
    FindOutTheme {
        AddQuestionItem(
            question = AddUiState.Question(
                id = 1,
                title = "Who's your daddy?"
            )
        )
    }
}