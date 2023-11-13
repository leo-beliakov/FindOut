package com.leoapps.findout.creation.form.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.leoapps.findout.creation.form.presentation.FormCreationScreen
import com.leoapps.findout.creation.question.navigation.QuestionCreationNavigatorImpl
import com.leoapps.findout.creation.question.presentation.QuestionCreationScreen

fun NavGraphBuilder.creationFeature(
    navController: NavController
) {
    navigation(
        startDestination = "form_creature",
        route = "creation_feature"
    ) {
        composable(
            route = "form_creature",
            enterTransition = {
                when (initialState.destination.route) {
                    "root" -> slideInVertically { it }
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "root" -> slideOutVertically { it }
                    else -> null
                }
            },
        ) {
            FormCreationScreen(
                navigator = FormCreationNavigatorImpl(navController)
            )
        }
        composable(
            route = "question_creature",
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } },
        ) {
            QuestionCreationScreen(
                navigator = QuestionCreationNavigatorImpl(navController),
            )
        }
    }
}
