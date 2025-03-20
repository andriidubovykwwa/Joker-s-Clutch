package com.devname.screen.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.components.BackButton
import com.devname.components.CardInfoDialog
import com.devname.components.DefeatDialog
import com.devname.components.EnergyDisplay
import com.devname.components.HandComponent
import com.devname.components.HealthBar
import com.devname.components.StatDisplay
import com.devname.components.VictoryDialog
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.PlayerStats
import com.devname.navigation.Screen
import com.devname.screen.game.view_model.GameEvent
import com.devname.screen.game.view_model.GameViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.attack
import jokersclutch.composeapp.generated.resources.defense
import jokersclutch.composeapp.generated.resources.enemy
import jokersclutch.composeapp.generated.resources.game_bg
import jokersclutch.composeapp.generated.resources.icon_attack_card_1
import jokersclutch.composeapp.generated.resources.player
import jokersclutch.composeapp.generated.resources.punch_effect
import jokersclutch.composeapp.generated.resources.shield_icon
import jokersclutch.composeapp.generated.resources.sword_icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    var showSlash by remember { mutableStateOf(false) }
    val obtainEvent = viewModel::obtainEvent
    val animatingCardIndex by viewModel.animatingCardIndex.collectAsState()
    val isShuffleAnimationActive by viewModel.isShuffleAnimationActive.collectAsState()
    LaunchedEffect(state.isTurnEnded) {
        if (state.playerHealth > 0 && state.enemyHealth > 0) {
            if (state.playerAttack > 0) {
                SoundController.playSlash(state.sounds)
                showSlash = true
                delay(350)
                showSlash = false
            }
            obtainEvent(GameEvent.SetupNewTurn)
        }
    }
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.game_bg),
            contentScale = ContentScale.FillBounds
        )
    ) {
        Column(Modifier.align(Alignment.TopCenter)) {
            Row(
                Modifier.fillMaxWidth().background(Color(0xB3000000))
                    .padding(
                        top = WindowInsets.safeContent.asPaddingValues().calculateTopPadding(),
                        start = WindowInsets.safeContent.asPaddingValues()
                            .calculateStartPadding(LayoutDirection.Ltr),
                        end = WindowInsets.safeContent.asPaddingValues()
                            .calculateEndPadding(LayoutDirection.Ltr),
                        bottom = 5.dp
                    ),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                StatDisplay(
                    drawableRes = Res.drawable.shield_icon,
                    description = "${stringResource(Res.string.enemy)} ${stringResource(Res.string.defense)}",
                    value = state.enemyBlock
                )
                HealthBar(
                    Modifier.weight(1f),
                    value = state.enemyHealth,
                    maxValue = state.enemy.startHealth
                )
                StatDisplay(
                    drawableRes = Res.drawable.sword_icon,
                    description = "${stringResource(Res.string.enemy)} ${stringResource(Res.string.attack)}",
                    value = state.enemyAttack
                )
            }
            BackButton(
                Modifier.padding(
                    start = WindowInsets.safeContent.asPaddingValues()
                        .calculateStartPadding(LayoutDirection.Ltr), top = 5.dp
                ).size(40.dp)
            ) {
                SoundController.playClick(state.sounds)
                navController.popBackStack()
            }

        }
        Column(
            Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxHeight(0.35f)
                    .graphicsLayer { translationY = size.height * 0.16f },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxHeight(),
                    painter = painterResource(state.enemy.imageRes),
                    contentDescription = stringResource(state.enemy.titleRes),
                    contentScale = ContentScale.FillHeight
                )
                val punchVisibility by animateFloatAsState(
                    targetValue = if (showSlash) 1f else 0f,
                    animationSpec = tween(durationMillis = 350)
                )
                Image(
                    painter = painterResource(Res.drawable.icon_attack_card_1),
                    contentDescription = stringResource(Res.string.punch_effect),
                    modifier = Modifier
                        .fillMaxSize(0.3f)
                        .alpha(punchVisibility)
                        .scale(punchVisibility)
                )
            }
            Column(
                Modifier.fillMaxWidth().background(Color(0xB3000000)).padding(
                    bottom = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding(),
                    start = WindowInsets.safeContent.asPaddingValues()
                        .calculateStartPadding(LayoutDirection.Ltr),
                    end = WindowInsets.safeContent.asPaddingValues()
                        .calculateEndPadding(LayoutDirection.Ltr),
                    top = 5.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    StatDisplay(
                        drawableRes = Res.drawable.shield_icon,
                        description = "${stringResource(Res.string.player)} ${stringResource(Res.string.defense)}",
                        value = state.playerAttack,
                        mirror = true
                    )
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        EnergyDisplay(
                            Modifier.size(50.dp),
                            value = state.playerEnergy,
                            maxValue = PlayerStats.START_ENERGY
                        )
                        Spacer(Modifier.height(5.dp))
                        HealthBar(
                            Modifier.fillMaxWidth(),
                            value = state.playerHealth,
                            maxValue = PlayerStats.START_HEALTH
                        )
                    }
                    StatDisplay(
                        drawableRes = Res.drawable.sword_icon,
                        description = "${stringResource(Res.string.player)} ${stringResource(Res.string.attack)}",
                        value = state.playerAttack,
                        mirror = true
                    )
                }
                HandComponent(
                    Modifier.fillMaxWidth(),
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
                    isShuffleAnimationActive = isShuffleAnimationActive,
                )
            }
        }
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
