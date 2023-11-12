package com.leoapps.findout.root.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.leoapps.findout.root.presentation.RootScreen

fun NavGraphBuilder.rootScreen(navController: NavHostController) {
    composable(route = "root") {
        RootScreen(
            navigator = RootNavigatorImpl(navController)
        )
    }
}