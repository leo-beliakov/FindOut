package com.leoapps.home.navigation

import com.leoapps.home.navigation.model.HomeNavCommand
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

interface HomeNavigator {
    fun onNavCommand(command: HomeNavCommand)
}

class HomeNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator
) : HomeNavigator {

    override fun onNavCommand(command: HomeNavCommand) {
        when (command) {
            is HomeNavCommand.OpenQuizDetails -> TODO()
        }
    }
}