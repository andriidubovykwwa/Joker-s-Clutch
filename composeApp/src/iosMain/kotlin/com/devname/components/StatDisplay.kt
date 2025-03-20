package com.devname.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun StatDisplay(
    modifier: Modifier = Modifier,
    drawableRes: DrawableResource,
    description: String,
    value: Int,
    mirror: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (mirror) {
            AppText(
                text = value.toString(),
                color = Color.White,
                outlineColor = null,
                fontSize = 20.sp
            )
        }
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(drawableRes),
            contentDescription = description,
            contentScale = ContentScale.FillBounds
        )
        if (!mirror) {
            AppText(
                text = value.toString(),
                color = Color.White,
                outlineColor = null,
                fontSize = 20.sp
            )
        }
    }
}
