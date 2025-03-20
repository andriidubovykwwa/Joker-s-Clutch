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
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.end_turn
import jokersclutch.composeapp.generated.resources.left
import jokersclutch.composeapp.generated.resources.play_card
import jokersclutch.composeapp.generated.resources.right
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
    isShuffleAnimationActive: Boolean,
) {
    val cardOffsetWidthX by animateFloatAsState(
        targetValue = if (isShuffleAnimationActive) -1f else 0f,
        animationSpec = tween(durationMillis = if (!isShuffleAnimationActive) DisplayInfo.SHUFFLE_ANIMATION_TIME.toInt() else 0)
    )
    Column(modifier, verticalArrangement = Arrangement.spacedBy(5.dp)) {
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(enabled = displayStart != 0, onClick = {
                onSwipeLeft()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(Res.string.left),
                    tint = if (displayStart != 0) Color.White else Color.Gray
                )
            }
            GameButton(
                Modifier.width(130.dp),
                text = stringResource(Res.string.play_card),
                onClick = {
                    onPlaySelectedCard()
                })
            GameButton(
                Modifier.width(130.dp),
                text = stringResource(Res.string.end_turn),
                onClick = {
                    onEndTurn()
                })
            IconButton(
                enabled = hand.size > DisplayInfo.CARD_IN_HAND_MAX && displayStart != hand.size - DisplayInfo.CARD_IN_HAND_MAX,
                onClick = {
                    onSwipeRight()
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(Res.string.right),
                    tint = if (hand.size > DisplayInfo.CARD_IN_HAND_MAX && displayStart != hand.size - DisplayInfo.CARD_IN_HAND_MAX) Color.White else Color.Gray
                )
            }
        }
    }
}