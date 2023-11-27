package com.leoapps.findout.navigation

import com.leoapps.creation.CreationFeatureNavGraph
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.mediapicker.detail.navigation.ImageDetailNavigator
import com.leoapps.mediapicker.detail.navigation.model.ImageDetailNavCommand
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigator(
    private val navigator: DestinationsNavigator,
) : RootNavigator,
    ImageDetailNavigator {

    override fun openAdd() {
        navigator.navigate(CreationFeatureNavGraph)
    }

    override fun onNavCommand(command: ImageDetailNavCommand) {
        when (command) {
            ImageDetailNavCommand.GoBack -> navigator.popBackStack()
        }
    }
}