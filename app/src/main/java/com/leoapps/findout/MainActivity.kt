package com.leoapps.findout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.leoapps.findout.add.presentation.AddScreen
import com.leoapps.findout.ui.theme.FindOutTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindOutTheme {
                AddScreen()
            }
        }
    }
}