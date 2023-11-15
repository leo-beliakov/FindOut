package com.leoapps.creation.form.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry
import com.leoapps.creation.CreationFeatureNavGraph
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.utils.navGraph

object FormCreationTransitions : DestinationStyle.Animated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        val isEnteringFeature = initialState.navGraph() != CreationFeatureNavGraph
        return if (isEnteringFeature) slideInVertically { it } else fadeIn()
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        val isLeavingFeature = targetState.navGraph() != CreationFeatureNavGraph
        return if (isLeavingFeature) slideOutVertically { it } else fadeOut()
    }
}