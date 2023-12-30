package com.leoapps.home.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoapps.home.navigation.HomeNavGraph
import com.leoapps.home.navigation.HomeNavigator
import com.leoapps.home.presentation.model.HomeUiAction
import com.leoapps.home.presentation.model.HomeUiState
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest


@HomeNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: HomeNavigator,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = viewModel::onAction
    )

    LaunchedEffect(Unit) {
        viewModel.navCommand.collectLatest { command ->
            navigator.onNavCommand(command)
        }
    }
}

@Composable
private fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeUiAction) -> Unit
) {

    LazyColumn {
        items(state.quizzes){
            Text(text = it.name ?: "n/a")
        }
    }
}