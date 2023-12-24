package com.leoapps.findout.presentation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.leoapps.creation.destinations.FormCreationScreenDestination
import com.leoapps.creation.destinations.QuestionCreationScreenDestination
import com.leoapps.creation.form.navigation.FormCreationNavigatorImpl
import com.leoapps.creation.form.presentation.FormCreationScreen
import com.leoapps.creation.question.navigation.QuestionCreationNavigatorImpl
import com.leoapps.creation.question.presentation.QuestionCreationScreen
import com.leoapps.design_system.theme.FindOutTheme
import com.leoapps.findout.navigation.MainNavGraph
import com.leoapps.findout.navigation.MainNavigator
import com.leoapps.mediapicker.root.navigation.PickerNavigatorImpl
import com.leoapps.mediapicker.root.presentation.destinations.PickerRootScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.scope.resultBackNavigator
import com.ramcosta.composedestinations.scope.resultRecipient
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
                        dependency(MainNavigator(destinationsNavigator))
                        dependency(PickerNavigatorImpl(resultBackNavigator()))
                    }
                ) {
                    composable(FormCreationScreenDestination) {
                        FormCreationScreen(
                            navigator = FormCreationNavigatorImpl(destinationsNavigator),
                            resultRecipient = resultRecipient<PickerRootScreenDestination, Uri>()
                        )
                    }
                    composable(QuestionCreationScreenDestination) {
                        QuestionCreationScreen(
                            navigator = QuestionCreationNavigatorImpl(destinationsNavigator),
                            resultRecipient = resultRecipient<PickerRootScreenDestination, Uri>()
                        )
                    }
                }
            }
        }
    }
}