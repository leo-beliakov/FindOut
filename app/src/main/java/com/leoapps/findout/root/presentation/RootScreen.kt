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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leoapps.findout.home.presentation.HomeScreen
import com.leoapps.findout.profile.presentation.ProfileScreen
import com.leoapps.findout.root.navigation.RootNavigatorImpl

@Composable
fun RootScreen(navigator: RootNavigatorImpl) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                    onClick = { navController.navigate("home") },
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
                    selected = currentDestination?.hierarchy?.any { it.route == "add" } == true,
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
                    selected = currentDestination?.hierarchy?.any { it.route == "profile" } == true,
                    onClick = { navController.navigate("profile") },
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
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings)
            ) {
                composable("home") {
                    HomeScreen()
                }
                composable("profile") {
                    ProfileScreen()
                }
            }
        }
    )
}