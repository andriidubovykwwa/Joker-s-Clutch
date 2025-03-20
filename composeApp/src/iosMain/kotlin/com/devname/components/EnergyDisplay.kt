package com.devname.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    val shape = CircleShape
    Box(
        modifier.background(Color(0xff1064A5), shape).border(2.dp, Color.White, shape).clip(shape),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = if (maxValue != null) "$value/$maxValue" else value.toString(),
            fontSize = fontSize,
            color = Color.White,
            outlineColor = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}