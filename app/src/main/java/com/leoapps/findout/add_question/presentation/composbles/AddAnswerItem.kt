package com.leoapps.findout.add_question.presentation.composbles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiState
import com.leoapps.findout.ui.theme.FindOutTheme
import java.util.UUID

@Composable
fun AddAnswerItem(
    answer: AddQuestionUiState.Answer,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(12.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = answer.title,
            style = MaterialTheme.typography.bodyLarge,
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
        AddAnswerItem(
            answer = AddQuestionUiState.Answer(
                id = UUID.randomUUID(),
                title = "Who's your daddy?"
            ),
            onClick = {}
        )
    }
}