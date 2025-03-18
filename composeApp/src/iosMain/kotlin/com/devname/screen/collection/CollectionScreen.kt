package com.devname.screen.collection

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.components.CardComponent
import com.devname.components.CardInfoDialog
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import com.devname.data.game_configuration.Enemy
import com.devname.screen.collection.view_model.CollectionEvent
import com.devname.screen.collection.view_model.CollectionViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CollectionScreen(
    navController: NavController,
    viewModel: CollectionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeContent.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
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
                                    .alpha(if (unlocked) 1f else 0.6f)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = { obtainEvent(CollectionEvent.DisplayCard(card)) }
                                        )
                                    },
                                card = card,
                            )
                            if (!unlocked) {
                                val enemyName = Enemy.entries.find { it.lvl == card.lvlToUnlock }
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "Defeat $enemyName to unlock",
                                    textAlign = TextAlign.Center
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