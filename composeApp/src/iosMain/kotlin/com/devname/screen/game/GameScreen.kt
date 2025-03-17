package com.devname.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devname.components.HandComponent
import com.devname.screen.game.view_model.GameEvent
import com.devname.screen.game.view_model.GameViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    LaunchedEffect(state.isTurnEnded) {
        if (state.playerHealth > 0 && state.enemyHealth > 0) {
            // TODO: animations?
            obtainEvent(GameEvent.SetupNewTurn)
        }
    }
    Box(Modifier.fillMaxSize().background(Color.White)) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        Text(
            modifier = Modifier.align(Alignment.TopCenter).padding(start = 80.dp),
            text = state.copy(
                startDeck = emptyList(),
                playerDeck = emptyList(),
                playerHand = emptyList()
            ).toString(),
            fontSize = 10.sp
        )
        HandComponent(
            Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(bottom = 20.dp),
            hand = state.playerHand,
            selectedCardIndex = state.selectedCardIndex,
            onSelectCard = { obtainEvent(GameEvent.SelectCard(it)) },
            onPlaySelectedCard = { obtainEvent(GameEvent.PlaySelectedCard) },
            onEndTurn = { obtainEvent(GameEvent.EndTurn) },
            onSwipeLeft = { obtainEvent(GameEvent.SwipeHandLeft) },
            onSwipeRight = { obtainEvent(GameEvent.SwipeHandRight) },
            displayStart = state.displayHandStartIndex
        )
    }
    if (state.playerHealth == 0) {
        // Defeat dialog
        LaunchedEffect(Unit) { println("defeat") }
    } else if (state.enemyHealth == 0) {
        // Victory dialog
        LaunchedEffect(Unit) { println("victory") }
    }
}
