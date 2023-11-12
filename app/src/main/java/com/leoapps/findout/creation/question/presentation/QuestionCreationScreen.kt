package com.leoapps.findout.creation.question.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.components.input.model.InputFieldState
import com.leoapps.findout.creation.answer.presentation.AnswerCreationDialog
import com.leoapps.findout.creation.form.presentation.composables.addImageSection
import com.leoapps.findout.creation.form.presentation.composables.titleSection
import com.leoapps.findout.creation.question.navigation.QuestionCreationNavigator
import com.leoapps.findout.creation.question.presentation.composbles.QuestionTopBar
import com.leoapps.findout.creation.question.presentation.composbles.answersSection
import com.leoapps.findout.creation.question.presentation.composbles.questionTypeSection
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.findout.creation.question.presentation.model.QuestionCreationUiState
import com.leoapps.findout.creation.question.presentation.model.QuestionType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuestionCreationScreen(
    navigator: QuestionCreationNavigator,
    viewModel: QuestionCreationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuestionCreationScreen(
        state = state,
        onAction = viewModel::onAction
    )

    state.dialogState?.let {
        AnswerCreationDialog(
            state = it,
            onAction = viewModel::onAction
        )
    }

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}

@Composable
fun QuestionCreationScreen(
    state: QuestionCreationUiState,
    onAction: (QuestionCreationUiAction) -> Unit
) {
    val scrollState = rememberLazyListState()
    val isButtonEnabled by remember {
        derivedStateOf {
            state.title.isNotEmpty() &&
                    (state.selectedQuestionType == QuestionType.OPEN_ANSWER || state.answers.isNotEmpty())
        }
    }

    Scaffold(
        topBar = {
            QuestionTopBar(
                isContentScrolled = scrollState.canScrollBackward,
                onAction = onAction
            )
        },
        content = { paddings ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    state = scrollState,
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        bottom = BOTTOM_GRADIENT_HEIGHT_DP.dp
                    ),
                ) {
                    addImageSection(
                        imageUri = state.coverUri,
                        onClick = { onAction(QuestionCreationUiAction.AddImageClicked) }
                    )
                    questionTypeSection(
                        selectedType = state.selectedQuestionType,
                        availableTypes = state.avaliableQuestionTypes,
                        onTypeSelected = { onAction(QuestionCreationUiAction.OnTypeSelected(it)) }
                    )
                    titleSection(
                        titleState = InputFieldState(
                            label = "Question",
                            value = state.title,
                            placeholder = "Enter question title",
                        ),
                        descriptionState = InputFieldState(
                            label = "Description",
                            value = state.description,
                            placeholder = "Enter question description",
                        ),
                        hasDescription = state.hasDescription,
                        onTitleChange = { onAction(QuestionCreationUiAction.TitleUpdated(it)) },
                        onAddDescriptionClick = { onAction(QuestionCreationUiAction.AddDescriptionClicked) },
                        onDescriptionChange = {
                            onAction(
                                QuestionCreationUiAction.DescriptionUpdated(
                                    it
                                )
                            )
                        },
                    )
                    if (state.selectedQuestionType != QuestionType.OPEN_ANSWER) {
                        answersSection(
                            answers = state.answers,
                            onAction = onAction
                        )
                    }
                }
                BottomButton(
                    text = "Add Question",
                    enabled = isButtonEnabled,
                    onClick = { onAction(QuestionCreationUiAction.OnAddQuestionClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}
