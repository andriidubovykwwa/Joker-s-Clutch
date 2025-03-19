package com.devname.screen.info

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
import com.devname.screen.info.view_model.InfoViewModel
import com.devname.utils.SoundController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InfoScreen(navController: NavController, viewModel: InfoViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize().safeContentPadding(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Info")
            Button(onClick = {
                navController.popBackStack()
                SoundController.playClick(state.sounds)
            }) {
                Text(text = "Back")
            }
        }
    }
}