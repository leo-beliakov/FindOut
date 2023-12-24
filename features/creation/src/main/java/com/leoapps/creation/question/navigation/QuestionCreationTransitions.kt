package com.leoapps.creation.question.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry
import com.leoapps.mediapicker.root.presentation.PickerNavGraph
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.utils.destination
import com.ramcosta.composedestinations.utils.startDestination

object QuestionCreationTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when (initialState.destination()) {
            PickerNavGraph.startDestination -> {
                fadeIn()
            }

            else -> {
                slideInVertically { it }
            }
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return when (targetState.destination()) {
            PickerNavGraph.startDestination -> {
                fadeOut()
            }

            else -> {
                slideOutVertically { it }
            }
        }
    }
}