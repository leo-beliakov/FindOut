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
import com.leoapps.findout.add.navigation.AddNavigatorImpl
import com.leoapps.findout.add.presentation.AddScreen
import com.leoapps.findout.add_question.navigation.AddQuestionNavigatorImpl
import com.leoapps.findout.add_question.presentation.AddQuestionScreen
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
                    addScreens(navController)
                }
            }
        }
    }

    fun NavGraphBuilder.addScreens(
        navController: NavController
    ) {
        navigation(
            startDestination = "add_survey",
            route = "add_feature"
        ) {
            composable(route = "add_survey") {
                AddScreen(
                    navigator = AddNavigatorImpl(navController)
                )
            }
            composable(
                route = "add_question",
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it }
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it }
                    )
                },
            ) {
                AddQuestionScreen(
                    navigator = AddQuestionNavigatorImpl(navController),
                )
            }
        }
    }
}