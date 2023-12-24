package com.leoapps.home.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoapps.home.navigation.HomeNavGraph
import com.leoapps.home.navigation.HomeNavigator
import com.ramcosta.composedestinations.annotation.Destination


@HomeNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: HomeNavigator,
) {
}