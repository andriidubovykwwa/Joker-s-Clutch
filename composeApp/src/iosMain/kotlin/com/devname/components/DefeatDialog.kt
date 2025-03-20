package com.devname.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.defeat
import jokersclutch.composeapp.generated.resources.defeat_text
import jokersclutch.composeapp.generated.resources.dialog_bg
import jokersclutch.composeapp.generated.resources.home
import jokersclutch.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DefeatDialog(modifier: Modifier = Modifier, onRestart: () -> Unit, onHome: () -> Unit) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier.widthIn(min = 150.dp).paint(
                painter = painterResource(Res.drawable.dialog_bg),
                contentScale = ContentScale.FillBounds
            ).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppText(
                text = stringResource(Res.string.defeat).uppercase(),
                outlineColor = null,
                color = Color(0xffFF0000),
                fontSize = 22.sp
            )
            Spacer(Modifier.height(5.dp))
            AppText(
                text = stringResource(Res.string.defeat_text),
                outlineColor = null,
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(5.dp))
            MenuButton(
                Modifier.width(120.dp),
                text = stringResource(Res.string.retry),
                onClick = onRestart,
                outlineColor = null,
                color = Color(0xff3CFF00)
            )
            Spacer(Modifier.height(5.dp))
            MenuButton(
                Modifier.width(120.dp),
                text = stringResource(Res.string.home),
                onClick = onHome,
                outlineColor = null,
                color = Color(0xffFFEE00)
            )
        }
    }
}