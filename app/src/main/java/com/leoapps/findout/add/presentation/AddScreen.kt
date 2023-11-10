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
import com.leoapps.findout.add.presentation.composables.AddCreateButton
import com.leoapps.findout.add.presentation.composables.AddTopBar
import com.leoapps.findout.add.presentation.composables.BOTTOM_GRADIENT_HEIGHT_DP
import com.leoapps.findout.add.presentation.composables.addImageSection
import com.leoapps.findout.add.presentation.composables.addQuestionsSection
import com.leoapps.findout.add.presentation.composables.addTitleSection

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
                        onAction = viewModel::onAction
                    )
                    addTitleSection(
                        title = state.title,
                        description = state.description,
                        hasDescription = state.hasDescription,
                        onAction = viewModel::onAction
                    )
                    addQuestionsSection(
                        questions = state.questions,
                        onAction = viewModel::onAction
                    )
                }
                AddCreateButton(
                    onAction = viewModel::onAction,
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    )
}