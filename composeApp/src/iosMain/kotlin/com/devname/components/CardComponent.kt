package com.devname.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.data.game_configuration.Card
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    card: Card,
    fontSizeScale: Float = 1f,
) {
    val shape = RoundedCornerShape(10.dp)
    Column(
        modifier
            .aspectRatio(0.625f)
            .background(Color(0xffD9BDA5), shape)
            .clip(shape)
            .padding(2.dp)
    ) {
        Row(Modifier.weight(25f).fillMaxWidth().padding(top = 3.dp)) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = card.energyCost.toString(),
                    color = Color.Blue,
                    fontSize = (10 * fontSizeScale).sp
                )
            }
            Box(Modifier.weight(9f), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(card.titleRes).uppercase(),
                    fontSize = (10 * fontSizeScale).sp,
                    textAlign = TextAlign.Center,
                    lineHeight = (10 * fontSizeScale).sp
                )
            }
        }
        Box(Modifier.weight(50f).fillMaxWidth().padding(8.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(card.imageRes),
                contentDescription = stringResource(card.titleRes),
                contentScale = ContentScale.FillHeight
            )
        }
        Box(Modifier.weight(25f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(card.descriptionRes),
                fontSize = (9 * fontSizeScale).sp,
                textAlign = TextAlign.Center,
                lineHeight = (9 * fontSizeScale).sp
            )
        }
    }
}