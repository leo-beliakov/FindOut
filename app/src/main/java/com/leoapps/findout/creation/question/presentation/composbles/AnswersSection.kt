package com.leoapps.findout.creation.question.presentation.composbles

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
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiState

internal fun LazyListScope.answersSection(
    answers: List<QuestionCreationUiState.Answer>,
    onAction: (QuestionCreationUiAction) -> Unit
) {
    item(key = "AnswersTitle") {
        Text(
            text = "Answers",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    items(
        items = answers,
        key = { answer -> answer.id }
    ) {
        AnswerItem(
            answer = it,
            onClick = { onAction(QuestionCreationUiAction.OnAnswerClicked(it)) }
        )
    }
    item(key = "AddAnswerButton") {
        AddAnswerButton(
            onClick = { onAction(QuestionCreationUiAction.AddAnswerClicked) }
        )
    }
}

@Composable
fun AddAnswerButton(
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
            text = "Add Answer",
            style = MaterialTheme.typography.labelLarge,
            color = com.leoapps.design_system.theme.Violet
        )
    }
}
