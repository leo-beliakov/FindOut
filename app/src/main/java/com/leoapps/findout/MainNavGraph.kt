package com.leoapps.findout

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.findout.destinations.RootScreenDestination
import com.leoapps.findout.root.presentation.RootNavGraph
import com.leoapps.mediapicker.root.presentation.PickerNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.ramcosta.composedestinations.utils.startDestinationSpec

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
