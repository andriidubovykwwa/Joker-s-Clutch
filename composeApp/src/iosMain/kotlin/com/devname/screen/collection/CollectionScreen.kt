package com.devname.screen.collection

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
import com.devname.screen.collection.view_model.CollectionViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CollectionScreen(
    navController: NavController,
    viewModel: CollectionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize().safeContentPadding(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Collection")
            Text(text = "Last completed lvl: ${state.lastCompletedLvl}")
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
        }
    }
}