package com.leoapps.findout.creation.form.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry
import com.leoapps.findout.appDestination
import com.leoapps.findout.destinations.RootScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

object FormCreationTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when (initialState.appDestination()) {
            RootScreenDestination -> slideInVertically { it }
            else -> fadeIn()
        }
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return when (targetState.appDestination()) {
            RootScreenDestination -> slideOutVertically { it }
            else -> fadeOut()
        }
    }
}