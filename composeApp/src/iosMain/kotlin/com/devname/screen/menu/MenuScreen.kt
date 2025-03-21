package com.devname.screen.menu

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.components.AnimatedGlow
import com.devname.components.MenuButton
import com.devname.components.OptionDialog
import com.devname.navigation.Screen
import com.devname.screen.menu.view_model.MenuEvent
import com.devname.screen.menu.view_model.MenuViewModel
import com.devname.utils.SoundController
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.app_name
import jokersclutch.composeapp.generated.resources.app_title
import jokersclutch.composeapp.generated.resources.collection
import jokersclutch.composeapp.generated.resources.info
import jokersclutch.composeapp.generated.resources.menu_bg
import jokersclutch.composeapp.generated.resources.options
import jokersclutch.composeapp.generated.resources.play
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val obtainEvent = viewModel::obtainEvent
    var isOptionsOpened by remember { mutableStateOf(false) }
    LaunchedEffect(state.music) {
        SoundController.playMusic(state.music)
    }
    Column(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.menu_bg),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val titleRotation by infiniteTransition.animateFloat(
            initialValue = -10f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            modifier = Modifier.height(220.dp).graphicsLayer {
                rotationY = titleRotation
            },
            painter = painterResource(Res.drawable.app_title),
            contentDescription = stringResource(Res.string.app_name),
            contentScale = ContentScale.FillHeight
        )
        Column(
            Modifier.width(230.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val glowThickness = 100f
            val shape = RoundedCornerShape(10.dp)
            var buttonSize by remember { mutableStateOf(IntSize.Zero) }
            val offsetX by infiniteTransition.animateFloat(
                initialValue = buttonSize.width * 1.4f,
                targetValue = -buttonSize.width * 0.4f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000),
                    repeatMode = RepeatMode.Restart
                )
            )
            Box(Modifier.fillMaxWidth().onSizeChanged { buttonSize = it }) {
                MenuButton(
                    Modifier.fillMaxWidth(),
                    onClick = {
                        SoundController.playClick(state.sounds)
                        navController.navigate(Screen.LevelSelector)
                    },
                    text = stringResource(Res.string.play)
                )
                AnimatedGlow(offsetX, glowThickness, shape)
            }
            Box(Modifier.fillMaxWidth()) {
                MenuButton(
                    Modifier.fillMaxWidth(),
                    onClick = {
                        SoundController.playClick(state.sounds)
                        navController.navigate(Screen.Collection)
                    },
                    text = stringResource(Res.string.collection)
                )
                AnimatedGlow(offsetX, glowThickness, shape)
            }
            Box(Modifier.fillMaxWidth()) {
                MenuButton(
                    Modifier.fillMaxWidth(),
                    onClick = {
                        SoundController.playClick(state.sounds)
                        navController.navigate(Screen.Info)
                    },
                    text = stringResource(Res.string.info)
                )
                AnimatedGlow(offsetX, glowThickness, shape)
            }
            Box(Modifier.fillMaxWidth()) {
                MenuButton(
                    Modifier.fillMaxWidth(),
                    onClick = {
                        SoundController.playClick(state.sounds)
                        isOptionsOpened = true
                    },
                    text = stringResource(Res.string.options)
                )
                AnimatedGlow(offsetX, glowThickness, shape)
            }
        }
    }
    if (isOptionsOpened) {
        OptionDialog(
            music = state.music,
            sounds = state.sounds,
            onSetMusic = { obtainEvent(MenuEvent.SetMusic(it)) },
            onSetSounds = { obtainEvent(MenuEvent.SetSounds(it)) },
            onDismiss = {
                SoundController.playClick(state.sounds)
                isOptionsOpened = false
            }
        )
    }
}