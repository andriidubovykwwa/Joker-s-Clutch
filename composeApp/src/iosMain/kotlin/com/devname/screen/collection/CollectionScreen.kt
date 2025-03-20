package com.devname.screen.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import com.devname.data.game_configuration.Enemy
import com.devname.screen.collection.view_model.CollectionEvent
import com.devname.screen.collection.view_model.CollectionViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.collection
import jokersclutch.composeapp.generated.resources.menu_bg
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CollectionScreen(
    navController: NavController,
    viewModel: CollectionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    LazyColumn(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.menu_bg),
            contentScale = ContentScale.FillBounds
        ).background(Color(0xB3000000)),
        contentPadding = WindowInsets.safeContent.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
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
                    text = stringResource(Res.string.collection).uppercase(),
                    fontSize = 22.sp,
                    color = Color.White,
                    outlineColor = null
                )
            }
        }
        val size = Card.entries.size
        var rows = size / DisplayInfo.CARD_IN_ROW_MAX_COLLECTION
        if (size % DisplayInfo.CARD_IN_ROW_MAX_COLLECTION > 0) rows += 1
        items(rows) { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(DisplayInfo.CARD_IN_ROW_MAX_COLLECTION) { column ->
                    val index = row * DisplayInfo.CARD_IN_ROW_MAX_COLLECTION + column
                    if (index < size) {
                        val card = Card.entries[index]
                        val unlocked = state.lastCompletedLvl >= card.lvlToUnlock
                        Box(Modifier.weight(1f)) {
                            CardComponent(
                                Modifier
                                    .fillMaxWidth()
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = { obtainEvent(CollectionEvent.DisplayCard(card)) }
                                        )
                                    }
                                    .rotate(if (state.displayCard == card) DisplayInfo.SELECTED_CARD_ROTATION else 0f)
                                    .scale(if (state.displayCard == card) DisplayInfo.SELECTED_CARD_SCALE else 1f),
                                card = card,
                            )
                            if (!unlocked) {
                                Box(
                                    Modifier.matchParentSize()
                                        .background(Color(0xE6000000), RoundedCornerShape(15.dp))
                                )
                                val enemy = Enemy.entries.find { it.lvl == card.lvlToUnlock }
                                    ?: Enemy.entries.last()
                                AppText(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "Defeat ${stringResource(enemy.titleRes)} to unlock",
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    outlineColor = null,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    } else Box(Modifier.weight(1f))
                }
            }
        }
    }
    state.displayCard?.let {
        CardInfoDialog(
            card = it,
            onDismiss = { obtainEvent(CollectionEvent.DisplayCard(null)) })
    }
}