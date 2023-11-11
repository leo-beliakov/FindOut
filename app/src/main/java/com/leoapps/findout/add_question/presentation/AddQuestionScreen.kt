package com.leoapps.findout.add_question.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.findout.add.presentation.composables.addImageSection
import com.leoapps.findout.add.presentation.composables.addTitleSection
import com.leoapps.findout.add_question.presentation.composbles.AddQuestionTopBar
import com.leoapps.findout.add_question.presentation.composbles.addAnswersSection
import com.leoapps.findout.add_question.presentation.model.AddQuestionUiAction
import com.leoapps.findout.design_system.components.button.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.findout.design_system.components.button.BottomButton
import com.leoapps.findout.design_system.components.input.model.InputFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuestionScreen(
    viewModel: AddQuestionViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            AddQuestionTopBar(
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
                        onClick = { viewModel.onAction(AddQuestionUiAction.AddImageClicked) }
                    )
                    addQuestionTypeSection(
                        selectedType = "Single Answer",
                        onTypeSelected = {}
                    )
                    addTitleSection(
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
                        onTitleChange = { viewModel.onAction(AddQuestionUiAction.TitleUpdated(it)) },
                        onAddDescriptionClick = { viewModel.onAction(AddQuestionUiAction.AddDescriptionClicked) },
                        onDescriptionChange = {
                            viewModel.onAction(
                                AddQuestionUiAction.DescriptionUpdated(
                                    it
                                )
                            )
                        },
                    )
                    addAnswersSection(
                        answers = emptyList(),
                        onAction = viewModel::onAction
                    )
                }
                BottomButton(
                    text = "Add Question",
                    onClick = { viewModel.onAction(AddQuestionUiAction.OnAddQuestionClicked) },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}