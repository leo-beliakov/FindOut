package com.leoapps.findout.root.presentation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.findout.destinations.ProfileScreenDestination
import com.leoapps.home.presentation.HomeNavGraph
import com.leoapps.mediapicker.root.presentation.PickerNavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph : NavGraphSpec {
    override val route = "myroot"

    override val startRoute: Route = HomeNavGraph

    override val destinationsByRoute = mapOf(
        ProfileScreenDestination.route to ProfileScreenDestination
    )

    override val nestedNavGraphs = listOf(
        HomeNavGraph,
        CreationFeatureNavGraph,
        PickerNavGraph,
    )
}
