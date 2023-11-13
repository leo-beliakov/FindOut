package com.leoapps.findout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.findout.creation.form.navigation.creationFeature
import com.leoapps.findout.root.navigation.rootScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FindOutTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "root"
                ) {
                    rootScreen(navController)
                    creationFeature(navController)
                }
            }
        }
    }
}