package com.devname.plinjump.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.devname.plinjump.screen.game.GameScreen
import com.devname.plinjump.utils.OrientationHandler

@Composable
fun Navigation() {
    LaunchedEffect(Unit) {
        OrientationHandler().orientation = OrientationHandler.Orientation.PORTRAIT
    }
    GameScreen()
}