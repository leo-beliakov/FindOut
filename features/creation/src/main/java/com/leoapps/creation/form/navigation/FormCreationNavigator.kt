package com.leoapps.creation.form.navigation

import com.leoapps.creation.form.navigation.model.FormCreationNavCommand

interface FormCreationNavigator {
    fun onNavigationCommand(command: FormCreationNavCommand)
}
