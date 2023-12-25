package com.leoapps.findout.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.mediapicker.root.presentation.PickerNavGraph
import com.leoapps.root.presentation.destinations.RootScreenDestination
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object MainNavGraph : NavGraphSpec {
    override val route = "main"

    override val startRoute: Route = RootScreenDestination

    override val destinationsByRoute = mapOf(
        RootScreenDestination.route to RootScreenDestination
    )

    override val nestedNavGraphs = listOf(
        CreationFeatureNavGraph,
        PickerNavGraph,
    )
}