package com.leoapps.findout.root.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.leoapps.findout.destinations.ProfileScreenDestination
import com.leoapps.findout.root.navigation.RootNavigator
import com.leoapps.home.navigation.HomeNavigatorImpl
import com.leoapps.home.presentation.HomeScreen
import com.leoapps.home.presentation.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.startDestination

@Destination
@Composable
fun RootScreen(
    navigator: RootNavigator
) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination =
                    navController.currentDestinationAsState().value ?: RootNavGraph.startDestination

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
                    selected = false,
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
                navGraph = RootNavGraph,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddings.calculateBottomPadding())
            ) {
                composable(HomeScreenDestination) {
                    HomeScreen(
                        navigator = HomeNavigatorImpl(destinationsNavigator),
                    )
                }
            }
        }
    )
}