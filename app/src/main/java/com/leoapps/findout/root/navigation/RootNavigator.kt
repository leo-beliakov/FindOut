package com.leoapps.findout.root.navigation

import androidx.navigation.NavController

interface RootNavigator {
    fun openAdd()
}

class RootNavigatorImpl(
    private val navController: NavController
) : RootNavigator {
    override fun openAdd() {
        navController.navigate("creation_feature") {
            launchSingleTop = true
        }
    }

}