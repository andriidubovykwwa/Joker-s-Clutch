package com.devname.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnergyDisplay(
    modifier: Modifier = Modifier,
    value: Int,
    maxValue: Int? = null,
    fontSize: TextUnit = 15.sp
) {
    val filling = if (maxValue != null) value / maxValue.toFloat()
    else 1f
    Box(
        modifier = modifier.border(2.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize().clip(CircleShape)) {
            val fillHeight = size.height * filling
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff1064A5),
                        Color(0xff031c4a)
                    )
                ),
                size = Size(width = size.width, height = fillHeight),
                topLeft = Offset(0f, size.height - fillHeight)
            )
        }

        // Display the value in the center
        AppText(
            text = if (maxValue != null) "$value/$maxValue" else value.toString(),
            fontSize = fontSize,
            lineHeight = fontSize,
            color = Color.White,
            outlineColor = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}