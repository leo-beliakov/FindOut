package com.leoapps.creation.form.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leoapps.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.creation.form.presentation.model.FormCreationUiState
import com.leoapps.design_system.theme.Violet

internal fun LazyListScope.questionsSection(
    questions: List<FormCreationUiState.Question>,
    onAction: (FormCreationUiAction) -> Unit
) {
    item(key = "QuestionsTitle") {
        Text(
            text = "Questions",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    items(
        items = questions,
        key = { question -> question.id }
    ) {
        QuestionItem(
            question = it,
            onClick = { onAction(FormCreationUiAction.OnQuestionClicked(it)) },
            onDismiss = { onAction(FormCreationUiAction.OnQuestionDismissed(it)) }
        )
    }
    item(key = "AddQuestionButton") {
        AddQuestionButton(
            onClick = { onAction(FormCreationUiAction.AddQuestionClicked) }
        )
    }
}

@Composable
fun AddQuestionButton(
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
            text = "Add Question",
            style = MaterialTheme.typography.labelLarge,
            color = Violet
        )
    }
}
