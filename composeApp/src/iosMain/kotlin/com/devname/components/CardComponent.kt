package com.devname.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    card: Card,
    fontSizeScale: Float = 1f,
    isPlayAnimationActive: Boolean = false
) {
    val scale by animateFloatAsState(
        targetValue = if (isPlayAnimationActive) 0f else 1f,
        animationSpec = tween(durationMillis = if (isPlayAnimationActive) DisplayInfo.PLAY_CARD_ANIMATION_TIME.toInt() else 0),
    )
    var cardHeight by remember { mutableStateOf(0) }
    val offsetY by animateFloatAsState(
        targetValue = if (isPlayAnimationActive) -(cardHeight * 1.5f) else 0f,
        animationSpec = tween(durationMillis = if (isPlayAnimationActive) DisplayInfo.PLAY_CARD_ANIMATION_TIME.toInt() else 0)
    )
    val shape = RoundedCornerShape(15.dp)
    Column(
        modifier
            .graphicsLayer(scaleX = scale, scaleY = scale, translationY = offsetY)
            .aspectRatio(0.56f)
            .onSizeChanged { cardHeight = it.height }
            .paint(
                painter = painterResource(card.imageRes),
                contentScale = ContentScale.FillBounds
            )
            .border(3.dp, card.borderColor, shape)
            .clip(shape)
            .padding(2.dp)
    ) {
        Box(Modifier.weight(2f).padding(top = 4.dp, start = 4.dp)) {
            EnergyDisplay(
                Modifier.size((28 * fontSizeScale).dp),
                value = card.energyCost,
                fontSize = (12 * fontSizeScale).sp
            )
        }
        Box(Modifier.weight(5f))
        Row(
            Modifier.weight(3f).fillMaxWidth().background(Color(0x80000000)),
        ) {
            Box(
                Modifier.weight(6f).fillMaxHeight().padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                AppText(
                    text = stringResource(card.descriptionRes),
                    fontSize = (8 * fontSizeScale).sp,
                    textAlign = TextAlign.Center,
                    outlineColor = null,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = (8 * fontSizeScale).sp
                )
            }
            Box(
                Modifier.weight(4f).fillMaxHeight().padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxHeight(0.6f),
                    painter = painterResource(card.iconRes),
                    contentDescription = stringResource(card.titleRes),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}