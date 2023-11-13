package com.leoapps.findout.root.navigation

import com.leoapps.findout.NavGraphs
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface RootNavigator {
    fun openAdd()
}

class RootNavigatorImpl(
    private val navController: DestinationsNavigator,
) : RootNavigator {

    override fun openAdd() {
        navController.navigate(NavGraphs.creationFeature) {
            launchSingleTop = true
        }
    }
}