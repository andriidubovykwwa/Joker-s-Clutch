package com.devname.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    energy: Int,
    isShuffleAnimationActive: Boolean,
) {
    val currentDisplayStart by rememberUpdatedState(displayStart)
    val currentEnergy by rememberUpdatedState(energy)
    val cardOffsetWidthX by animateFloatAsState(
        targetValue = if (isShuffleAnimationActive) -1f else 0f,
        animationSpec = tween(durationMillis = if (!isShuffleAnimationActive) DisplayInfo.SHUFFLE_ANIMATION_TIME.toInt() else 0)
    )
    val infiniteTransition = rememberInfiniteTransition()
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
                val currentCardEnergyCost by rememberUpdatedState(card.energyCost)
                Box(
                    Modifier.weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { onDisplayCard(card) },
                                onTap = {
                                    if (currentEnergy >= currentCardEnergyCost) {
                                        onSelectCard(displayIndex + currentDisplayStart)
                                    }
                                }
                            )
                        }
                        .graphicsLayer {
                            rotationX =
                                if (selectedCardIndex == displayIndex + currentDisplayStart) DisplayInfo.SELECTED_CARD_ROTATION else 0f
                            rotationY =
                                if (selectedCardIndex == displayIndex + currentDisplayStart) DisplayInfo.SELECTED_CARD_ROTATION else 0f
                            rotationZ =
                                if (selectedCardIndex == displayIndex + currentDisplayStart) DisplayInfo.SELECTED_CARD_ROTATION else 0f
                            scaleX =
                                if (selectedCardIndex == displayIndex + currentDisplayStart) DisplayInfo.SELECTED_CARD_SCALE else 1f
                            scaleY =
                                if (selectedCardIndex == displayIndex + currentDisplayStart) DisplayInfo.SELECTED_CARD_SCALE else 1f
                        },
                ) {
                    var cardSize by remember { mutableStateOf(IntSize.Zero) }
                    CardComponent(
                        Modifier
                            .fillMaxWidth()
                            .onSizeChanged { cardSize = it },
                        card = card,
                        isPlayAnimationActive = displayIndex + currentDisplayStart == playAnimationIndex
                    )
                    if (currentEnergy < card.energyCost) {
                        Box(
                            Modifier.matchParentSize()
                                .background(Color(0xE6000000), RoundedCornerShape(15.dp))
                                .padding(5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(
                                modifier = Modifier.align(Alignment.Center),
                                text = "NO\nENERGY",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                outlineColor = null,
                                fontSize = 15.sp
                            )
                        }
                    }
                    if (selectedCardIndex == displayIndex + currentDisplayStart) {
                        val offsetX by infiniteTransition.animateFloat(
                            initialValue = cardSize.width * 1.4f,
                            targetValue = -cardSize.width * 0.4f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(3000),
                                repeatMode = RepeatMode.Restart
                            )
                        )
                        val glowThickness = 100f
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color(0x1fffffff),
                                            Color.Transparent
                                        ),
                                        start = Offset(offsetX, offsetX),
                                        end = Offset(
                                            offsetX + glowThickness,
                                            offsetX + glowThickness
                                        )
                                    ),
                                    RoundedCornerShape(15.dp)
                                ).clip(RoundedCornerShape(15.dp))
                        )

                    }
                }
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