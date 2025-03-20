package com.devname.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.unit.dp
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.button_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackButton(modifier: Modifier = Modifier, onBack: () -> Unit) {
    Box(
        modifier.paint(painter = painterResource(Res.drawable.button_back))
            .clip(RoundedCornerShape(10.dp))
            .clickable { onBack() }
    )
}