package com.devname.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo

@Composable
fun HandComponent(
    modifier: Modifier = Modifier,
    hand: List<Card>,
    onSelectCard: (Int) -> Unit,
    onPlaySelectedCard: () -> Unit,
    onEndTurn: () -> Unit,
    selectedCardIndex: Int?,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    displayStart: Int
) {
    Column(
        modifier
    ) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            val displayList =
                if (hand.size > DisplayInfo.CARD_IN_HAND_MAX) {
                    if (displayStart + DisplayInfo.CARD_IN_HAND_MAX - 1 < hand.size) {
                        hand.subList(displayStart, displayStart + DisplayInfo.CARD_IN_HAND_MAX)
                    } else {
                        // Rearrange card if one of the last cards was played
                        hand.take(DisplayInfo.CARD_IN_HAND_MAX)
                    }
                } else {
                    hand
                }
            displayList.forEachIndexed { displayIndex, it ->
                val index = displayIndex + displayStart
                CardComponent(
                    Modifier
                        .weight(1f)
                        .rotate(if (selectedCardIndex == index) 7f else 0f)
                        .scale(if (selectedCardIndex == index) 1.04f else 1f),
                    card = it,
                    onClick = { onSelectCard(index) }
                )
            }
            if (displayList.size < DisplayInfo.CARD_IN_HAND_MAX) { // To maintain card size
                repeat(DisplayInfo.CARD_IN_HAND_MAX - displayList.size) {
                    Box(Modifier.weight(1f))
                }
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onSwipeLeft() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Left"
                )
            }
            Button(onClick = { onPlaySelectedCard() }) {
                Text(text = "Play Card", fontSize = 10.sp)
            }
            Button(onClick = { onEndTurn() }) {
                Text(text = "End Turn", fontSize = 10.sp)
            }
            IconButton(onClick = { onSwipeRight() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Right"
                )
            }
        }
    }
}