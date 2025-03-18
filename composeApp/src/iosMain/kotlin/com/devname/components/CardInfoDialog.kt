package com.devname.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.devname.data.game_configuration.Card

@Composable
fun CardInfoDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit, card: Card) {
    Dialog(onDismissRequest = onDismiss) {
        CardComponent(
            modifier = modifier.fillMaxWidth(0.8f),
            card = card,
            fontSizeScale = 2f
        )
    }
}