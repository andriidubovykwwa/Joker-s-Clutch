package com.devname.screen.level_selector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.data.game_configuration.Enemy
import com.devname.navigation.Screen
import com.devname.screen.level_selector.view_model.LevelSelectorViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LevelSelectorScreen(
    navController: NavController,
    viewModel: LevelSelectorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize().safeContentPadding(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Level Selector")
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
            Enemy.entries.forEach {
                Button(
                    enabled = state.lastCompletedLvl + 1 >= it.lvl,
                    onClick = { navController.navigate(Screen.Game(it.lvl)) }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}