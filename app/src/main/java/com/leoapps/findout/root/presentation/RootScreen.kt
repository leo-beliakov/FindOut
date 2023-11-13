package com.leoapps.findout.root.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.leoapps.findout.NavGraphs
import com.leoapps.findout.appCurrentDestinationAsState
import com.leoapps.findout.destinations.FormCreationScreenDestination
import com.leoapps.findout.destinations.HomeScreenDestination
import com.leoapps.findout.destinations.ProfileScreenDestination
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.findout.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate

@RootNavGraph(start = true)
@Destination
@Composable
fun RootScreen(
    navigator: RootNavigator
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination = navController.appCurrentDestinationAsState().value
                    ?: NavGraphs.root.startAppDestination

                NavigationBarItem(
                    selected = currentDestination == HomeScreenDestination,
                    onClick = {
                        navController.navigate(HomeScreenDestination) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Home"
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentDestination == FormCreationScreenDestination,
                    onClick = { navigator.openAdd() },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddCircle,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Create"
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentDestination == ProfileScreenDestination,
                    onClick = {
                        navController.navigate(ProfileScreenDestination) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = "Profile"
                        )
                    }
                )
            }
        },
        content = { paddings ->
            DestinationsNavHost(
                navGraph = NavGraphs.myRoot,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddings.calculateBottomPadding())
            )
        }
    )
}