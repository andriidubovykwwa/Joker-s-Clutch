package com.devname.screen.menu

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
fun MenuScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = { navController.navigate(Screen.LevelSelector) }) {
                Text("Play")
            }
            Button(onClick = { navController.navigate(Screen.Collection) }) {
                Text("Collection")
            }
            Button(onClick = { navController.navigate(Screen.Info) }) {
                Text("Info")
            }
        }
    }
}