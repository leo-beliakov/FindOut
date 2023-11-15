package com.leoapps.findout

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.media_picker.presentation.PickerNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object MainNavGraph : NavGraphSpec {
    override val route = "main"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute: Route = NavGraphs.root

    override val nestedNavGraphs = listOf(
        NavGraphs.root,
        CreationFeatureNavGraph,
        PickerNavGraph,
    )
}
