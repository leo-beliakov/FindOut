package com.leoapps.findout.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leoapps.findout.root.navigation.MyRootNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@MyRootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    )
}