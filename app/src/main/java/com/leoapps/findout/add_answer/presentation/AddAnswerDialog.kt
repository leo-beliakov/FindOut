package com.leoapps.findout.add_answer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.leoapps.findout.add_answer.presentation.model.AnswerDialogState
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiAction
import com.leoapps.findout.design_system.components.input.InputField
import com.leoapps.findout.design_system.components.input.model.InputFieldState

@Composable
fun AddAnswerDialog(
    state: AnswerDialogState,
    onAction: (AddQuestionUiAction) -> Unit,
) {
    var enteredAnswer by rememberSaveable { mutableStateOf(state.answerText) }
    val isButtonEnabled by remember { derivedStateOf { enteredAnswer.isNotEmpty() } }
//    var isCorrectAnswer by rememberSaveable {
//        mutableStateOf(state.answer?.isCorrect ?: false)
//    }

    Dialog(
        onDismissRequest = { onAction(AddQuestionUiAction.OnDialogDismissed) }
    ) {
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
                    placeholder = "Enter the answer",
                    label = stringResource(id = state.titleResId),
                ),
            )
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Correct Answer",
//                    style = MaterialTheme.typography.titleMedium,
//                    modifier = Modifier.weight(1f, true)
//                )
//                Switch(
//                    checked = isCorrectAnswer,
//                    onCheckedChange = { isCorrectAnswer = it }
//                )
//            }
            Button(
                onClick = {
                    when (state) {
                        is AnswerDialogState.Create -> {
                            onAction(
                                AddQuestionUiAction.OnAddAnswerClicked(
                                    answer = enteredAnswer,
                                )
                            )
                        }

                        is AnswerDialogState.Edit -> {
                            onAction(
                                AddQuestionUiAction.OnEditAnswerClicked(
                                    answer = state.answer.copy(
                                        title = enteredAnswer,
                                    ),
                                )
                            )
                        }
                    }
                },
                enabled = isButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = state.buttonTextResId)
                )
            }
        }
    }
}