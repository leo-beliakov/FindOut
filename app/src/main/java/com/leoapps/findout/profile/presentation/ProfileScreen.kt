package com.leoapps.findout.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leoapps.findout.root.navigation.MyRootNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@MyRootNavGraph
@Destination
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    )
}