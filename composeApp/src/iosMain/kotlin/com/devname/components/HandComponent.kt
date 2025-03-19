package com.devname.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.end_turn
import jokersclutch.composeapp.generated.resources.play_card
import org.jetbrains.compose.resources.stringResource

@Composable
fun HandComponent(
    modifier: Modifier = Modifier,
    hand: List<Card>,
    onSelectCard: (Int) -> Unit,
    onDisplayCard: (Card) -> Unit,
    onPlaySelectedCard: () -> Unit,
    onEndTurn: () -> Unit,
    selectedCardIndex: Int?,
    playAnimationIndex: Int?,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    displayStart: Int,
    isShuffleAnimationActive: Boolean
) {
    val cardOffsetWidthX by animateFloatAsState(
        targetValue = if (isShuffleAnimationActive) -1f else 0f,
        animationSpec = tween(durationMillis = if (!isShuffleAnimationActive) DisplayInfo.SHUFFLE_ANIMATION_TIME.toInt() else 0)
    )
    Column(modifier) {
        Row(
            Modifier.fillMaxWidth().graphicsLayer {
                translationX = cardOffsetWidthX * size.width * 1.5f
            }.padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            val displayList =
                if (hand.size > DisplayInfo.CARD_IN_HAND_MAX) {
                    if (displayStart + DisplayInfo.CARD_IN_HAND_MAX - 1 < hand.size) {
                        hand.subList(displayStart, displayStart + DisplayInfo.CARD_IN_HAND_MAX)
                    } else {
                        hand.take(DisplayInfo.CARD_IN_HAND_MAX)
                    }
                } else {
                    hand
                }
            displayList.forEachIndexed { displayIndex, card ->
                val index = displayIndex + displayStart
                CardComponent(
                    Modifier
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { onDisplayCard(card) },
                                onTap = { onSelectCard(index) }
                            )
                        }
                        .rotate(if (selectedCardIndex == index) DisplayInfo.SELECTED_CARD_ROTATION else 0f)
                        .scale(if (selectedCardIndex == index) DisplayInfo.SELECTED_CARD_SCALE else 1f),
                    card = card,
                    isPlayAnimationActive = index == playAnimationIndex
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
                Text(text = stringResource(Res.string.play_card), fontSize = 10.sp)
            }
            Button(onClick = { onEndTurn() }) {
                Text(text = stringResource(Res.string.end_turn), fontSize = 10.sp)
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