package com.devname.screen.level_selector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.devname.components.LevelSelectorComponent
import com.devname.data.game_configuration.Enemy
import com.devname.navigation.Screen
import com.devname.screen.level_selector.view_model.LevelSelectorViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.level_selector_bg
import jokersclutch.composeapp.generated.resources.select_your_opponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LevelSelectorScreen(
    navController: NavController,
    viewModel: LevelSelectorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    LazyColumn(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.level_selector_bg),
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
                    text = stringResource(Res.string.select_your_opponent).uppercase(),
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
        items(Enemy.entries) {
            Column(
                Modifier.width(280.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (it.lvl == state.lastCompletedLvl + 2) {
                    val currentEnemyName = stringResource(it.titleRes)
                    val prevEnemy =
                        Enemy.entries.find { x -> x.lvl == it.lvl - 1 } ?: Enemy.entries.last()
                    val prevEnemyName = stringResource(prevEnemy.titleRes)
                    AppText(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                        text = "Defeat $prevEnemyName to unlock $currentEnemyName",
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        color = Color.White,
                        outlineColor = null
                    )
                }
                LevelSelectorComponent(
                    modifier = Modifier.fillMaxWidth(),
                    enemy = it,
                    unlocked = state.lastCompletedLvl + 1 >= it.lvl,
                    onClick = {
                        SoundController.playClick(state.sounds)
                        navController.navigate(Screen.Game(it.lvl))
                    }
                )
            }
        }
    }
}