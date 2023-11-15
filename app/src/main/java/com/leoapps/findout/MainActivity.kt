package com.leoapps.findout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.leoapps.creation.form.navigation.FormCreationNavigatorImpl
import com.leoapps.creation.question.navigation.QuestionCreationNavigatorImpl
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.findout.root.navigation.RootNavigatorImpl
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FindOutTheme {
                DestinationsNavHost(
                    navGraph = MainNavGraph,
                    dependenciesContainerBuilder = {
                        dependency(RootNavigatorImpl(destinationsNavigator))
                        dependency(FormCreationNavigatorImpl(destinationsNavigator))
                        dependency(QuestionCreationNavigatorImpl(destinationsNavigator))
                    }
                )
            }
        }
    }
}