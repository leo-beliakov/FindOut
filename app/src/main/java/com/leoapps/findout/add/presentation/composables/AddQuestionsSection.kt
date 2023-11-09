package com.leoapps.findout.add.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.add.presentation.model.AddUiState
import com.leoapps.findout.ui.theme.Violet

fun LazyListScope.addQuestionsSection(
    questions: List<AddUiState.Question>,
    onAction: (AddUiAction) -> Unit
) {
    item {
        Text(
            text = "Questions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    items(
        items = questions,
        key = { question -> question.id }
    ) {
        AddQuestionItem(
            question = it
        )
    }
    item {
        AddQuestionButton(
            onClick = { onAction(AddUiAction.AddQuestionClicked) }
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
            color = Violet
        )
    }
}
