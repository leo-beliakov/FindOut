package com.leoapps.findout.add.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.ui.theme.Violet

@Composable
fun AddQuestionsSection(
    questions: List<Any>,
    onAction: (AddUiAction) -> Unit
) {
    Text(
        text = "Questions",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    questions.forEach {

    }
    AddQuestionButton(
        onClick = {}
    )
}

@Composable
fun AddQuestionButton(
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
            text = "Add Question",
            color = Violet
        )
    }
}
