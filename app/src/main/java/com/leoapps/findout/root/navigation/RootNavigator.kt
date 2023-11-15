package com.leoapps.findout.root.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface RootNavigator {
    fun openAdd()
}

class RootNavigatorImpl(
    private val navController: DestinationsNavigator,
) : RootNavigator {

    override fun openAdd() {
        navController.navigate(CreationFeatureNavGraph) {
            launchSingleTop = true
        }
    }
}