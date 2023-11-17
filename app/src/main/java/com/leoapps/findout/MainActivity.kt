package com.leoapps.findout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.findout.navigation.MainNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FindOutTheme {
                val engine = rememberAnimatedNavHostEngine()
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = engine.rememberNavController(bottomSheetNavigator)

                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomSheetNavigator
                ) {
                    DestinationsNavHost(
                        navGraph = MainNavGraph,
                        navController = navController,
                        dependenciesContainerBuilder = {
                            dependency(MainNavigator(destinationsNavigator))
                        }
                    )
                }
            }
        }
    }
}