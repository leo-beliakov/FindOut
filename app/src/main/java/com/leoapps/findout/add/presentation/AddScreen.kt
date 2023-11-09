package com.leoapps.findout.add.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.findout.add.presentation.composables.AddImageSection
import com.leoapps.findout.add.presentation.composables.AddQuestionsSection
import com.leoapps.findout.add.presentation.composables.AddTitleSection
import com.leoapps.findout.add.presentation.composables.AddTopBar

@Composable
fun AddScreen(
    viewModel: AddSurveyScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AddTopBar(
                title = state.pageName,
                onAction = viewModel::onAction
            )
        },
        content = { paddings ->
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings)
                    .padding(16.dp)
            ) {
                AddImageSection(
                    imageUri = state.coverUri,
                    onAction = viewModel::onAction
                )
                AddTitleSection(
                    title = state.title,
                    description = state.description,
                    onAction = viewModel::onAction
                )
                AddQuestionsSection(
                    questions = state.questions,
                    onAction = viewModel::onAction
                )
            }
        }
    )
}