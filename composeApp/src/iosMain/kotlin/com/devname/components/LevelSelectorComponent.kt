package com.devname.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devname.data.game_configuration.Enemy
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.locked_enemy_frame
import jokersclutch.composeapp.generated.resources.unlocked_enemy_frame
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LevelSelectorComponent(
    modifier: Modifier = Modifier,
    enemy: Enemy,
    onClick: () -> Unit,
    unlocked: Boolean
) {
    val shape = RoundedCornerShape(15.dp)
    Row(
        modifier
            .aspectRatio(3.25f)
            .paint(
                painter = painterResource(if (unlocked) Res.drawable.unlocked_enemy_frame else Res.drawable.locked_enemy_frame),
                contentScale = ContentScale.FillBounds
            )
            .clip(shape).clickable(enabled = unlocked) { onClick() }
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f).padding(5.dp), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.height(80.dp),
                painter = painterResource(enemy.imageRes),
                contentScale = ContentScale.FillHeight,
                contentDescription = stringResource(enemy.titleRes),
            )
        }
        Box(Modifier.weight(3f), contentAlignment = Alignment.Center) {
            AppText(
                text = stringResource(enemy.titleRes),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}