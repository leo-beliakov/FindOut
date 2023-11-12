package com.leoapps.findout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.leoapps.findout.creation.form.navigation.FormCreationNavigatorImpl
import com.leoapps.findout.creation.form.presentation.FormCreationScreen
import com.leoapps.findout.creation.question.navigation.QuestionCreationNavigatorImpl
import com.leoapps.findout.creation.question.presentation.QuestionCreationScreen
import com.leoapps.findout.root.navigation.rootScreen
import com.leoapps.findout.ui.theme.FindOutTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindOutTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "root"
                ) {
                    rootScreen(navController)
                    creationFeature(navController)
                }
            }
        }
    }

    fun NavGraphBuilder.creationFeature(
        navController: NavController
    ) {
        navigation(
            startDestination = "form_creature",
            route = "creation_feature"
        ) {
            composable(
                route = "form_creature",
                enterTransition = { slideInVertically { it } },
                exitTransition = { slideOutVertically { it } },
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
}