package com.devname.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.music
import jokersclutch.composeapp.generated.resources.options
import jokersclutch.composeapp.generated.resources.sounds
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
    val shape = RoundedCornerShape(20.dp)
    Dialog(onDismissRequest = {
        SoundController.playClick(sounds)
        onDismiss()
    }) {
        Column(
            modifier.widthIn(min = 150.dp).background(Color(0xbbffffff), shape).padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(Res.string.options))
            Row {
                Text(stringResource(Res.string.music))
                IconButton(onClick = {
                    SoundController.playClick(sounds)
                    onSetMusic(music - 1)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Left"
                    )
                }
                Text(music.toString())
                IconButton(onClick = {
                    SoundController.playClick(sounds)
                    onSetMusic(music + 1)
                }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right"
                    )
                }
            }
            Row {
                Text(stringResource(Res.string.sounds))
                IconButton(onClick = {
                    SoundController.playClick(sounds)
                    onSetSounds(sounds - 1)
                }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Left"
                    )
                }
                Text(sounds.toString())
                IconButton(onClick = {
                    SoundController.playClick(sounds)
                    onSetSounds(sounds + 1)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Right"
                    )
                }
            }
        }
    }
}