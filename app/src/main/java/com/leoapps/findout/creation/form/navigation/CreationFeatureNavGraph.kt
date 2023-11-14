package com.leoapps.findout.creation.form.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@NavGraph
annotation class CreationFeatureNavGraph(
    val start: Boolean = false
)