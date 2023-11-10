package com.leoapps.findout.add.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.findout.add.presentation.composables.AddTopBar
import com.leoapps.findout.add.presentation.composables.addImageSection
import com.leoapps.findout.add.presentation.composables.addQuestionsSection
import com.leoapps.findout.add.presentation.composables.addTitleSection
import com.leoapps.findout.add.presentation.model.AddUiAction
import com.leoapps.findout.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.findout.design_system.components.button.BottomButton
import com.leoapps.findout.design_system.components.input.model.InputFieldState

@Composable
fun AddScreen(
    viewModel: AddSurveyScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            AddTopBar(
                title = state.pageName,
                isContentScrolled = scrollState.canScrollBackward,
                onAction = viewModel::onAction
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
                        onClick = { viewModel.onAction(AddUiAction.AddImageClicked) }
                    )
                    addTitleSection(
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
                        onTitleChange = { viewModel.onAction(AddUiAction.TitleUpdated(it)) },
                        onAddDescriptionClick = { viewModel.onAction(AddUiAction.AddDescriptionClicked) },
                        onDescriptionChange = { viewModel.onAction(AddUiAction.DescriptionUpdated(it)) },
                    )
                    addQuestionsSection(
                        questions = state.questions,
                        onAction = viewModel::onAction
                    )
                }
                BottomButton(
                    text = "Create",
                    onClick = { viewModel.onAction(AddUiAction.OnCreateClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}