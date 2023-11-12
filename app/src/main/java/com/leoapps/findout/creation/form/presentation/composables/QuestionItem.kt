package com.leoapps.findout.creation.form.presentation.composables

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
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiState
import com.leoapps.findout.ui.theme.FindOutTheme

@Composable
internal fun QuestionItem(
    question: FormCreationUiState.Question
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { }
            .padding(12.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = question.title,
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
        QuestionItem(
            question = FormCreationUiState.Question(
                id = 1,
                title = "Who's your daddy?"
            )
        )
    }
}