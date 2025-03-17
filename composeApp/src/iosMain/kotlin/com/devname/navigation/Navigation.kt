package com.devname.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devname.screen.collection.CollectionScreen
import com.devname.screen.game.GameScreen
import com.devname.screen.info.InfoScreen
import com.devname.screen.level_selector.LevelSelectorScreen
import com.devname.screen.menu.MenuScreen
import com.devname.utils.OrientationHandler

@Composable
fun Navigation() {
    LaunchedEffect(Unit) {
        OrientationHandler().orientation = OrientationHandler.Orientation.PORTRAIT
    }
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Menu
    ) {
        composable<Screen.Menu> {
            MenuScreen(navController = navController)
        }
        composable<Screen.Game> {
            GameScreen(navController = navController)
        }
        composable<Screen.LevelSelector> {
            LevelSelectorScreen(navController = navController)
        }
        composable<Screen.Info> {
            InfoScreen(navController = navController)
        }
        composable<Screen.Collection> {
            CollectionScreen(navController = navController)
        }
    }
}