package com.devname.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devname.data.game_configuration.Card

@Composable
fun CardInfoDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit, card: Card) {
    val infiniteTransition = rememberInfiniteTransition()
    val animDuration = 3000
    val glowThickness = 180f
    val rotation by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(animDuration),
            repeatMode = RepeatMode.Reverse
        )
    )
    var cardSize by remember { mutableStateOf(IntSize.Zero) }
    val offsetX by infiniteTransition.animateFloat(
        initialValue = cardSize.width * 1.4f,
        targetValue = -cardSize.width * 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(animDuration * 2),
            repeatMode = RepeatMode.Restart
        )
    )
    Dialog(onDismissRequest = onDismiss) {
        Box(modifier.fillMaxWidth(0.8f).onSizeChanged { cardSize = it }.graphicsLayer {
            rotationX = rotation
            rotationY = rotation
            rotationZ = rotation
        }) {
            CardComponent(
                modifier = Modifier.fillMaxWidth(),
                card = card,
                fontSizeScale = 2f
            )
            AnimatedGlow(offsetX, glowThickness, RoundedCornerShape(15.dp))
        }
    }
}