package com.devname.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun BoxScope.AnimatedGlow(offsetX: Float, thickness: Float, shape: Shape) {
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
                        offsetX + thickness,
                        offsetX + thickness
                    )
                ),
                shape
            ).clip(shape)
    )
}