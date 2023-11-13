package com.leoapps.findout.creation.form.presentation

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
import com.leoapps.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.components.input.model.InputFieldState
import com.leoapps.findout.creation.form.navigation.CreationFeatureNavGraph
import com.leoapps.findout.creation.form.navigation.FormCreationNavigator
import com.leoapps.findout.creation.form.navigation.FormCreationTransitions
import com.leoapps.findout.creation.form.presentation.composables.TopBar
import com.leoapps.findout.creation.form.presentation.composables.addImageSection
import com.leoapps.findout.creation.form.presentation.composables.questionsSection
import com.leoapps.findout.creation.form.presentation.composables.titleSection
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.findout.creation.form.presentation.model.FormCreationUiState
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest

@CreationFeatureNavGraph(start = true)
@Destination(style = FormCreationTransitions::class)
@Composable
fun FormCreationScreen(
    navigator: FormCreationNavigator,
    viewModel: FormCreationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FormCreationScreen(
        state = state,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavigationCommand(command)
        }
    }
}


@Composable
private fun FormCreationScreen(
    state: FormCreationUiState,
    onAction: (FormCreationUiAction) -> Unit
) {
    val scrollState = rememberLazyListState()
    val isButtonEnabled by remember {
        derivedStateOf { state.title.isNotEmpty() && state.questions.isNotEmpty() }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = state.pageNameResId),
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
                        onClick = { onAction(FormCreationUiAction.AddImageClicked) }
                    )
                    titleSection(
                        titleState = InputFieldState(
                            label = "Title",
                            value = state.title,
                            placeholder = "Enter survey title",
                        ),
                        descriptionState = InputFieldState(
                            label = "Description",
                            value = state.description,
                            placeholder = "Enter survey description",
                        ),
                        hasDescription = state.hasDescription,
                        onTitleChange = { onAction(FormCreationUiAction.TitleUpdated(it)) },
                        onAddDescriptionClick = { onAction(FormCreationUiAction.AddDescriptionClicked) },
                        onDescriptionChange = { onAction(FormCreationUiAction.DescriptionUpdated(it)) },
                    )
                    questionsSection(
                        questions = state.questions,
                        onAction = onAction
                    )
                }
                BottomButton(
                    text = "Create",
                    enabled = isButtonEnabled,
                    onClick = { onAction(FormCreationUiAction.OnCreateClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}