package com.leoapps.profile.navigation

import com.leoapps.profile.navigation.model.ProfileNavCommand
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface ProfileNavigator {
    fun onNavCommand(command: ProfileNavCommand)
}

class ProfileNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator
) : ProfileNavigator {
    override fun onNavCommand(command: ProfileNavCommand) {
        when (command) {
            else -> {}
        }
    }

}