package com.devname.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devname.data.game_configuration.Card
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.dialog_bg
import jokersclutch.composeapp.generated.resources.home
import jokersclutch.composeapp.generated.resources.new_card_unlocked
import jokersclutch.composeapp.generated.resources.retry
import jokersclutch.composeapp.generated.resources.victory
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun VictoryDialog(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit,
    onHome: () -> Unit,
    onDisplayCard: (Card) -> Unit,
    unlockedCards: List<Card>
) {
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
                text = stringResource(Res.string.victory).uppercase(),
                outlineColor = null,
                color = Color(0xff00FF11),
                fontSize = 22.sp
            )
            Spacer(Modifier.height(5.dp))
            if (unlockedCards.isNotEmpty()) {
                AppText(
                    text = stringResource(Res.string.new_card_unlocked),
                    fontSize = 16.sp,
                    color = Color.White,
                    outlineColor = null
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    unlockedCards.forEach { card ->
                        CardComponent(
                            modifier = Modifier.size(80.dp, 142.dp).pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = { onDisplayCard(card) },
                                )
                            },
                            card = card
                        )
                    }
                }
            }
            Spacer(Modifier.height(5.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(
                    Modifier.width(120.dp),
                    text = stringResource(Res.string.retry),
                    onClick = onRestart,
                    outlineColor = null,
                    color = Color(0xff3CFF00)
                )
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
}