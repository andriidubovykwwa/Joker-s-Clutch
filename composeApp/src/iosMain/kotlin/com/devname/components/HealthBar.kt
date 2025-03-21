package com.devname.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.health_frame
import org.jetbrains.compose.resources.painterResource

@Composable
fun HealthBar(modifier: Modifier = Modifier, value: Int, maxValue: Int) {
    val shape = RoundedCornerShape(32)
    Box(
        modifier.aspectRatio(4.61f).paint(
            painter = painterResource(Res.drawable.health_frame),
            contentScale = ContentScale.FillBounds
        )
    ) {
        val width = 0.92f
        val height = 0.68f
        Box(
            Modifier.align(Alignment.Center).fillMaxWidth(width).fillMaxHeight(height),
        ) {
            Box(
                Modifier.fillMaxWidth(value / maxValue.toFloat()).fillMaxHeight()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xffFF0004),
                                Color(0xff7F0002)
                            )
                        ),
                        shape
                    )
            )
            AppText(
                modifier = Modifier.align(Alignment.Center),
                text = "$value/$maxValue",
                color = Color.White,
                outlineColor = Color(0xff540101),
                fontSize = 20.sp
            )
        }
    }
}