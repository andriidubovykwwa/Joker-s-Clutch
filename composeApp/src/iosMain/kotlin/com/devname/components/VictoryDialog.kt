package com.devname.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.new_card_unlocked
import jokersclutch.composeapp.generated.resources.victory
import org.jetbrains.compose.resources.stringResource

@Composable
fun VictoryDialog(modifier: Modifier = Modifier, onRestart: () -> Unit, onHome: () -> Unit) {
    val shape = RoundedCornerShape(20.dp)
    Dialog(onDismissRequest = {}) {
        Column(
            modifier.widthIn(min = 150.dp).background(Color(0xbbffffff), shape).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(Res.string.victory))
            Text(stringResource(Res.string.new_card_unlocked))
            Button(onClick = onRestart) {
                Text("Restart")
            }
            Button(onClick = onHome) {
                Text("Home")
            }
        }
    }
}