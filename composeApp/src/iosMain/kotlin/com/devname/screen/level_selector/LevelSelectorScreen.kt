package com.devname.screen.level_selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.navigation.Screen

@Composable
fun LevelSelectorScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Level Selector")
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
            Button(onClick = { navController.navigate(Screen.Game(0)) }) {
                Text(text = "Enemy 1")
            }
            Button(onClick = { navController.navigate(Screen.Game(1)) }) {
                Text(text = "Enemy 2")
            }
            Button(onClick = { navController.navigate(Screen.Game(2)) }) {
                Text(text = "Enemy 3")
            }
            Button(onClick = { navController.navigate(Screen.Game(3)) }) {
                Text(text = "Enemy 4")
            }
            Button(onClick = { navController.navigate(Screen.Game(4)) }) {
                Text(text = "Enemy 5")
            }
        }
    }
}