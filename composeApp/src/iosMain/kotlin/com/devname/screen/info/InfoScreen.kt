package com.devname.screen.info

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devname.components.AppText
import com.devname.components.BackButton
import com.devname.components.CardComponent
import com.devname.components.CardInfoDialog
import com.devname.components.EnergyDisplay
import com.devname.components.HealthBar
import com.devname.components.StatDisplay
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import com.devname.data.game_configuration.Enemy
import com.devname.data.game_configuration.PlayerStats
import com.devname.screen.info.view_model.InfoEvent
import com.devname.screen.info.view_model.InfoViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.attack
import jokersclutch.composeapp.generated.resources.defense
import jokersclutch.composeapp.generated.resources.enemy
import jokersclutch.composeapp.generated.resources.info
import jokersclutch.composeapp.generated.resources.menu_bg
import jokersclutch.composeapp.generated.resources.shield_icon
import jokersclutch.composeapp.generated.resources.sword_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InfoScreen(navController: NavController, viewModel: InfoViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    var showElements by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { showElements = true }
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.menu_bg),
            contentScale = ContentScale.FillBounds
        ).background(Color(0xB3000000))
    ) {
        AnimatedVisibility(
            visible = showElements,
            enter = DisplayInfo.elementStartAnimation,
            exit = fadeOut()
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = WindowInsets.safeContent.asPaddingValues(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackButton(Modifier.size(40.dp)) {
                            SoundController.playClick(state.sounds)
                            navController.popBackStack()
                        }
                        AppText(
                            text = stringResource(Res.string.info).uppercase(),
                            fontSize = 22.sp,
                            color = Color.White,
                            outlineColor = null,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(10.dp))
                }
                val headerSize = 23.sp
                val defaultSize = 20.sp
                item {
                    AppText(
                        text = "OBJECTIVE",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = headerSize
                    )
                }
                item {
                    AppText(
                        text = "- Defeat your opponent by strategically playing attack, defense, and special cards. If an opponent’s health points reach 0, you win. If yours reach 0, you lose.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    HealthBar(
                        Modifier.fillMaxWidth(),
                        value = PlayerStats.START_HEALTH / 2,
                        maxValue = PlayerStats.START_HEALTH
                    )
                }
                item {
                    Spacer(Modifier.height(10.dp))
                }
                item {
                    AppText(
                        text = "GAMEPLAY",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = headerSize
                    )
                }
                item {
                    AppText(
                        text = "- You start with a deck containing ${PlayerStats.COPIES_OF_EACH_CARD_IN_START_DECK} copies of each available card.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    AppText(
                        text = "- Each turn, you draw ${PlayerStats.DRAW_CARD_PER_TURN} random cards from your deck.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    AppText(
                        text = "- You have ${PlayerStats.START_ENERGY} energy points per turn to play cards.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    EnergyDisplay(
                        Modifier.size(60.dp),
                        value = PlayerStats.START_ENERGY,
                        maxValue = PlayerStats.START_ENERGY
                    )
                }
                item {
                    AppText(
                        text = "- Attack cards deal damage at the end of the turn.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    CardComponent(
                        Modifier
                            .fillMaxWidth(0.4f)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        obtainEvent(InfoEvent.DisplayCard(Card.ATTACK_CARD_1))
                                    }
                                )
                            },
                        card = Card.ATTACK_CARD_1,
                    )
                }
                item {
                    AppText(
                        text = "- Defense cards reduce incoming damage.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    CardComponent(
                        Modifier
                            .fillMaxWidth(0.4f)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        obtainEvent(InfoEvent.DisplayCard(Card.DEFENSE_CARD_1))
                                    }
                                )
                            },
                        card = Card.DEFENSE_CARD_1,
                    )
                }
                item {
                    AppText(
                        text = "- Special cards can combine attack and defense or provide other effects (e.g., drawing extra cards).",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    CardComponent(
                        Modifier
                            .fillMaxWidth(0.4f)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        obtainEvent(InfoEvent.DisplayCard(Card.MISC_CARD_1))
                                    }
                                )
                            },
                        card = Card.MISC_CARD_1,
                    )
                }
                item {
                    AppText(
                        text = "- You can see the opponent’s upcoming attack and defense values to plan your moves.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatDisplay(
                            drawableRes = Res.drawable.shield_icon,
                            description = "${stringResource(Res.string.enemy)} ${stringResource(Res.string.defense)}",
                            value = 2
                        )
                        StatDisplay(
                            drawableRes = Res.drawable.sword_icon,
                            description = "${stringResource(Res.string.enemy)} ${stringResource(Res.string.attack)}",
                            value = 3
                        )
                    }
                }
                item {
                    AppText(
                        text = "- You can see the card details after long pressing on the card",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    Spacer(Modifier.height(10.dp))
                }
                item {
                    AppText(
                        text = "PROGRESSION",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    AppText(
                        text = "- There are ${Enemy.entries.size} opponents, each stronger than the last.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    AppText(
                        text = "- Defeat an opponent to unlock the next one.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
                item {
                    AppText(
                        text = "- Winning a match unlocks a new card, which is added to your deck.",
                        color = Color.White,
                        outlineColor = Color.Black,
                        fontSize = defaultSize
                    )
                }
            }
        }
    }
    state.displayCard?.let {
        CardInfoDialog(
            card = it,
            onDismiss = { obtainEvent(InfoEvent.DisplayCard(null)) })
    }
}