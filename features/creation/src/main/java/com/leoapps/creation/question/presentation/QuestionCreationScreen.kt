package com.leoapps.creation.question.presentation

import android.net.Uri
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.creation.answer.presentation.AnswerCreationDialog
import com.leoapps.creation.form.navigation.CreationFeatureNavGraph
import com.leoapps.creation.form.presentation.composables.addImageSection
import com.leoapps.creation.form.presentation.composables.titleSection
import com.leoapps.creation.question.navigation.QuestionCreationNavigator
import com.leoapps.creation.question.navigation.QuestionCreationTransitions
import com.leoapps.creation.question.presentation.composbles.TopBar
import com.leoapps.creation.question.presentation.composbles.answersSection
import com.leoapps.creation.question.presentation.composbles.questionTypeSection
import com.leoapps.creation.question.presentation.model.QuestionCreationUiAction
import com.leoapps.creation.question.presentation.model.QuestionCreationUiState
import com.leoapps.creation.question.presentation.model.QuestionType
import com.leoapps.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.components.input.model.InputFieldState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.OpenResultRecipient
import kotlinx.coroutines.flow.collectLatest

@CreationFeatureNavGraph
@Destination(
    navArgsDelegate = QuestionCreationArgs::class,
    style = QuestionCreationTransitions::class,
)
@Composable
fun QuestionCreationScreen(
    navigator: QuestionCreationNavigator,
    resultRecipient: OpenResultRecipient<Uri>,
    viewModel: QuestionCreationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    resultRecipient.onNavResult { result ->
        when (result) {
            NavResult.Canceled -> {}
            is NavResult.Value -> {
                viewModel.onAction(QuestionCreationUiAction.OnImageSelected(result.value))
            }
        }
    }

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

    //todo find out the reason, cuz without this additional state we're getting an IOBException
    //todo Below is kinda strange:
    val show by remember {
        derivedStateOf { state.selectedQuestionType != QuestionType.OPEN_ANSWER }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = state.screenTitleResId),
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
                        availableTypes = state.availableQuestionTypes,
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
                    if (show) {
                        answersSection(
                            answers = state.answers,
                            onAction = onAction
                        )
                    }
                }
                BottomButton(
                    text = stringResource(id = state.screenButtonResId),
                    enabled = state.title.isNotEmpty() &&
                            (state.selectedQuestionType == QuestionType.OPEN_ANSWER || state.answers.isNotEmpty()),
                    onClick = { onAction(QuestionCreationUiAction.OnAddQuestionClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}
