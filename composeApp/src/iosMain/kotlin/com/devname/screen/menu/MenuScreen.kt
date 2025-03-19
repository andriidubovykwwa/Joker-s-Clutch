package com.devname.screen.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.components.OptionDialog
import com.devname.navigation.Screen
import com.devname.screen.menu.view_model.MenuEvent
import com.devname.screen.menu.view_model.MenuViewModel
import com.devname.utils.SoundController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    var isOptionsOpened by remember { mutableStateOf(false) }
    LaunchedEffect(state.music) {
        SoundController.playMusic(state.music)
    }
    Box(Modifier.fillMaxSize().safeContentPadding(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = {
                SoundController.playClick(state.sounds)
                navController.navigate(Screen.LevelSelector)
            }) {
                Text("Play")
            }
            Button(onClick = {
                SoundController.playClick(state.sounds)
                navController.navigate(Screen.Collection)
            }) {
                Text("Collection")
            }
            Button(onClick = {
                SoundController.playClick(state.sounds)
                navController.navigate(Screen.Info)
            }) {
                Text("Info")
            }
            Button(onClick = {
                SoundController.playClick(state.sounds)
                isOptionsOpened = true
            }) {
                Text("Options")
            }
        }
    }
    if (isOptionsOpened) {
        OptionDialog(
            music = state.music,
            sounds = state.sounds,
            onSetMusic = { obtainEvent(MenuEvent.SetMusic(it)) },
            onSetSounds = { obtainEvent(MenuEvent.SetSounds(it)) },
            onDismiss = { isOptionsOpened = false }
        )
    }
}