package com.leoapps.creation.form.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoapps.creation.form.presentation.model.FormCreationUiState
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.design_system.theme.Pink80
import kotlinx.coroutines.delay
import java.util.UUID

private const val ANIMATION_DURATION_MS = 200
private const val DISMISS_THRESHOLD = 0.3f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuestionItem(
    question: FormCreationUiState.Question,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    var show by remember { mutableStateOf(true) }
    val dismissState = rememberDismissState(
        positionalThreshold = { total -> total * DISMISS_THRESHOLD },
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                show = false
                true
            } else {
                false
            }
        }
    )

    AnimatedVisibility(
        visible = show,
        exit = shrinkVertically(
            animationSpec = tween(ANIMATION_DURATION_MS),
            shrinkTowards = Alignment.CenterVertically,
        )
    ) {
        SwipeToDismiss(
            state = dismissState,
            directions = setOf(DismissDirection.EndToStart),
            background = {
                QuestionItemBackground(
                    direction = dismissState.dismissDirection,
                )
            },
            dismissContent = {
                QuestionItemForeground(
                    question = question,
                    onClick = onClick
                )
            },
        )
    }

    LaunchedEffect(key1 = show) {
        if (!show) {
            delay(ANIMATION_DURATION_MS.toLong())
            onDismiss()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuestionItemBackground(
    direction: DismissDirection?
) {
    val color = when (direction) {
        DismissDirection.EndToStart -> Pink80
        else -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier.weight(1f, true)
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete"
        )
    }
}

@Composable
private fun QuestionItemForeground(
    question: FormCreationUiState.Question,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
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
                id = UUID.randomUUID(),
                title = "Who's your daddy?"
            ),
            onClick = {},
            onDismiss = {}
        )
    }
}