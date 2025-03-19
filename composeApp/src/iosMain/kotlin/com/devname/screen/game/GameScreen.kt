package com.devname.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
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
import com.devname.components.CardInfoDialog
import com.devname.components.DefeatDialog
import com.devname.components.HandComponent
import com.devname.components.VictoryDialog
import com.devname.data.game_configuration.Card
import com.devname.navigation.Screen
import com.devname.screen.game.view_model.GameEvent
import com.devname.screen.game.view_model.GameViewModel
import com.devname.utils.SoundController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    val animatingCardIndex by viewModel.animatingCardIndex.collectAsState()
    val isShuffleAnimationActive by viewModel.isShuffleAnimationActive.collectAsState()
    LaunchedEffect(state.isTurnEnded) {
        if (state.playerHealth > 0 && state.enemyHealth > 0) {
            // TODO: attack animations?
            obtainEvent(GameEvent.SetupNewTurn)
        }
    }
    Box(Modifier.fillMaxSize().background(Color.White).safeContentPadding()) {
        Button(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = {
                SoundController.playClick(state.sounds)
                navController.popBackStack(Screen.Menu, false)
            }
        ) {
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
            Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            hand = state.playerHand,
            selectedCardIndex = state.selectedCardIndex,
            onSelectCard = { obtainEvent(GameEvent.SelectCard(it)) },
            onDisplayCard = { obtainEvent(GameEvent.DisplayCard(it)) },
            onPlaySelectedCard = { obtainEvent(GameEvent.PlaySelectedCard) },
            onEndTurn = { obtainEvent(GameEvent.EndTurn) },
            onSwipeLeft = { obtainEvent(GameEvent.SwipeHandLeft) },
            onSwipeRight = { obtainEvent(GameEvent.SwipeHandRight) },
            displayStart = state.displayHandStartIndex,
            playAnimationIndex = animatingCardIndex,
            isShuffleAnimationActive = isShuffleAnimationActive
        )
    }
    if (state.playerHealth == 0) {
        DefeatDialog(
            onRestart = {
                SoundController.playClick(state.sounds)
                obtainEvent(GameEvent.Restart)
            },
            onHome = {
                SoundController.playClick(state.sounds)
                navController.popBackStack(Screen.Menu, false)
            }
        )
    } else if (state.enemyHealth == 0) {
        VictoryDialog(
            onRestart = {
                SoundController.playClick(state.sounds)
                obtainEvent(GameEvent.Restart)
            },
            onHome = {
                SoundController.playClick(state.sounds)
                navController.popBackStack(Screen.Menu, false)
            },
            unlockedCards = if (state.newCardUnlocked) Card.entries.filter { it.lvlToUnlock == state.enemy.lvl }
            else emptyList(),
            onDisplayCard = { obtainEvent(GameEvent.DisplayCard(it)) },
        )
    }
    state.displayCard?.let {
        CardInfoDialog(
            card = it,
            onDismiss = { obtainEvent(GameEvent.DisplayCard(null)) })
    }
}
