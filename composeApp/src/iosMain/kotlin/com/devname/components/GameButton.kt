package com.devname.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.button_bg
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    Box(
        modifier
            .aspectRatio(3.25f)
            .paint(
                painter = painterResource(Res.drawable.button_bg),
                contentScale = ContentScale.FillBounds
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            text = text,
            fontSize = 15.sp,
            color = Color.White,
            outlineColor = null
        )
    }
}