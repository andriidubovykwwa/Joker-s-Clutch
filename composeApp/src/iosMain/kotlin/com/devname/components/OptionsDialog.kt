package com.devname.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.dialog_bg
import jokersclutch.composeapp.generated.resources.left
import jokersclutch.composeapp.generated.resources.music
import jokersclutch.composeapp.generated.resources.options
import jokersclutch.composeapp.generated.resources.right
import jokersclutch.composeapp.generated.resources.save
import jokersclutch.composeapp.generated.resources.sounds
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OptionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSetMusic: (Int) -> Unit,
    onSetSounds: (Int) -> Unit,
    music: Int,
    sounds: Int,
) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier.widthIn(min = 220.dp).heightIn(min = 150.dp).paint(
                painter = painterResource(Res.drawable.dialog_bg),
                contentScale = ContentScale.FillBounds
            ).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppText(
                text = stringResource(Res.string.options).uppercase(),
                fontSize = 22.sp,
                color = Color.White,
                outlineColor = null
            )
            Spacer(Modifier.height(25.dp))
            Row(
                Modifier.widthIn(min = 150.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.weight(2f), contentAlignment = Alignment.Center) {
                    AppText(
                        text = stringResource(Res.string.music).uppercase(),
                        fontSize = 18.sp,
                        color = Color.White,
                        outlineColor = null
                    )
                }
                Row(
                    Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        SoundController.playClick(sounds)
                        onSetMusic(music - 1)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(Res.string.left),
                            tint = Color.White
                        )
                    }
                    AppText(
                        text = music.toString(),
                        fontSize = 16.sp,
                        color = Color.White,
                        outlineColor = null
                    )
                    IconButton(onClick = {
                        SoundController.playClick(sounds)
                        onSetMusic(music + 1)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(Res.string.right),
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.widthIn(min = 150.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.weight(2f), contentAlignment = Alignment.Center) {
                    AppText(
                        text = stringResource(Res.string.sounds).uppercase(),
                        fontSize = 18.sp,
                        color = Color.White,
                        outlineColor = null
                    )
                }
                Row(
                    Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        SoundController.playClick(sounds)
                        onSetSounds(sounds - 1)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(Res.string.left),
                            tint = Color.White
                        )
                    }
                    AppText(
                        text = sounds.toString(),
                        fontSize = 16.sp,
                        color = Color.White,
                        outlineColor = null
                    )
                    IconButton(onClick = {
                        SoundController.playClick(sounds)
                        onSetSounds(sounds + 1)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = stringResource(Res.string.right),
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            MenuButton(
                Modifier.width(120.dp),
                text = stringResource(Res.string.save),
                onClick = { onDismiss() },
                outlineColor = null,
                color = Color(0xff3CFF00)
            )
        }
    }
}