package com.devname.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devname.components.AppText
import com.devname.components.BackButton
import com.devname.data.game_configuration.Enemy
import com.devname.data.game_configuration.PlayerStats
import com.devname.screen.info.view_model.InfoViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.info
import jokersclutch.composeapp.generated.resources.menu_bg
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InfoScreen(navController: NavController, viewModel: InfoViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    LazyColumn(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.menu_bg),
            contentScale = ContentScale.FillBounds
        ).background(Color(0xB3000000)),
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
            AppText(
                text = "- Attack cards deal damage at the end of the turn.",
                color = Color.White,
                outlineColor = Color.Black,
                fontSize = defaultSize
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
            AppText(
                text = "- Special cards can combine attack and defense or provide other effects (e.g., drawing extra cards).",
                color = Color.White,
                outlineColor = Color.Black,
                fontSize = defaultSize
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