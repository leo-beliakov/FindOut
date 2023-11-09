package com.leoapps.findout.add.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.findout.add.presentation.composables.AddTopBar
import com.leoapps.findout.add.presentation.composables.addImageSection
import com.leoapps.findout.add.presentation.composables.addQuestionsSection
import com.leoapps.findout.add.presentation.composables.addTitleSection

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
            LazyColumn( //todo add content type??
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings)
            ) {
                addImageSection(
                    imageUri = state.coverUri,
                    onAction = viewModel::onAction
                )
                addTitleSection(
                    title = state.title,
                    description = state.description,
                    onAction = viewModel::onAction
                )
                addQuestionsSection(
                    questions = state.questions,
                    onAction = viewModel::onAction
                )
            }
        }
    )
}