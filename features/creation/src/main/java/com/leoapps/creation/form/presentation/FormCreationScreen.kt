package com.leoapps.creation.form.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.creation.R
import com.leoapps.creation.form.navigation.CreationFeatureNavGraph
import com.leoapps.creation.form.navigation.FormCreationNavigator
import com.leoapps.creation.form.navigation.FormCreationTransitions
import com.leoapps.creation.form.presentation.composables.TopBar
import com.leoapps.creation.form.presentation.composables.addImageSection
import com.leoapps.creation.form.presentation.composables.questionsSection
import com.leoapps.creation.form.presentation.composables.titleSection
import com.leoapps.creation.form.presentation.model.FormCreationUiAction
import com.leoapps.creation.form.presentation.model.FormCreationUiState
import com.leoapps.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.design_system.components.button.BottomButton
import com.leoapps.design_system.components.input.model.InputFieldState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.OpenResultRecipient
import kotlinx.coroutines.flow.collectLatest

@CreationFeatureNavGraph(start = true)
@Destination(
    style = FormCreationTransitions::class,
    navArgsDelegate = FormCreationArgs::class
)
@Composable
fun FormCreationScreen(
    navigator: FormCreationNavigator,
    resultRecipient: OpenResultRecipient<Uri>,
    viewModel: FormCreationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    resultRecipient.onNavResult { result ->
        when (result) {
            NavResult.Canceled -> {}
            is NavResult.Value -> {
                viewModel.onAction(FormCreationUiAction.OnImageSelected(result.value))
            }
        }
    }

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

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = state.pageNameResId),
                isElevated = scrollState.canScrollBackward,
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
                            labelResId = R.string.form_creation_title_label,
                            value = state.title,
                            placeholderResId = R.string.form_creation_title_placeholder,
                        ),
                        descriptionState = InputFieldState(
                            labelResId = R.string.form_creation_description_label,
                            value = state.description,
                            placeholderResId = R.string.form_creation_description_placeholder,
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
                    text = stringResource(id = R.string.form_creation_button),
                    enabled = state.title.isNotEmpty() && state.questions.isNotEmpty(), //todo check if derived state would be better
                    onClick = { onAction(FormCreationUiAction.OnCreateClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}