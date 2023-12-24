package com.leoapps.root.navigation

import com.leoapps.home.presentation.HomeNavGraph
import com.leoapps.profile.presentaion.ProfileNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph : NavGraphSpec {
    override val route = "myroot"

    override val startRoute: Route = HomeNavGraph

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val nestedNavGraphs = listOf(
        HomeNavGraph,
        ProfileNavGraph
    )
}
