package com.leoapps.creation.answer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.leoapps.creation.R
import com.leoapps.creation.answer.presentation.model.AnswerCreationState
import com.leoapps.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.design_system.components.input.InputField
import com.leoapps.design_system.components.input.model.InputFieldState

@Composable
fun AnswerCreationDialog(
    state: AnswerCreationState,
    onAction: (QuestionCreationUiAction) -> Unit,
) {
    var enteredAnswer by rememberSaveable { mutableStateOf(state.answerText) }
    var isCorrectAnswer by rememberSaveable { mutableStateOf(state.isCorrect) }

    Dialog(onDismissRequest = { onAction(QuestionCreationUiAction.OnDialogDismissed) }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            InputField(
                onValueChange = { enteredAnswer = it },
                state = InputFieldState(
                    value = enteredAnswer,
                    placeholderResId = R.string.answer_creation_input_placeholder,
                    labelResId = state.titleResId,
                ),
            )
            if (state.isCorrectShown) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Correct Answer",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f, true)
                    )
                    Switch(
                        checked = isCorrectAnswer,
                        onCheckedChange = { isCorrectAnswer = it }
                    )
                }

            }
            Button(
                onClick = {
                    when (state) {
                        is AnswerCreationState.Create -> {
                            onAction(
                                QuestionCreationUiAction.OnAddAnswerClicked(
                                    answer = enteredAnswer,
                                )
                            )
                        }

                        is AnswerCreationState.Edit -> {
                            onAction(
                                QuestionCreationUiAction.OnEditAnswerClicked(
                                    answer = state.answer.copy(
                                        title = enteredAnswer,
                                    ),
                                )
                            )
                        }
                    }
                },
                enabled = enteredAnswer.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = state.buttonTextResId)
                )
            }
        }
    }
}