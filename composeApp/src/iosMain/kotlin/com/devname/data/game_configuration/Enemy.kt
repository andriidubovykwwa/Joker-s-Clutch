package com.devname.data.game_configuration

import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.enemy_1_name
import jokersclutch.composeapp.generated.resources.enemy_2_name
import jokersclutch.composeapp.generated.resources.enemy_3_name
import jokersclutch.composeapp.generated.resources.enemy_4_name
import jokersclutch.composeapp.generated.resources.enemy_5_name
import jokersclutch.composeapp.generated.resources.enemy_picture_1
import jokersclutch.composeapp.generated.resources.enemy_picture_2
import jokersclutch.composeapp.generated.resources.enemy_picture_3
import jokersclutch.composeapp.generated.resources.enemy_picture_4
import jokersclutch.composeapp.generated.resources.enemy_picture_5
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Enemy(
    val lvl: Int,
    val titleRes: StringResource,
    val imageRes: DrawableResource,
    val startHealth: Int,
    val minAttackDefenseValue: Int,
    val maxAttackDefenseValue: Int,
) {
    ENEMY_1(
        lvl = 0,
        titleRes = Res.string.enemy_1_name,
        imageRes = Res.drawable.enemy_picture_1,
        startHealth = 25,
        minAttackDefenseValue = 2,
        maxAttackDefenseValue = 3
    ),
    ENEMY_2(
        lvl = 1,
        titleRes = Res.string.enemy_2_name,
        imageRes = Res.drawable.enemy_picture_2,
        startHealth = 40,
        minAttackDefenseValue = 3,
        maxAttackDefenseValue = 5
    ),
    ENEMY_3(
        lvl = 2,
        titleRes = Res.string.enemy_3_name,
        imageRes = Res.drawable.enemy_picture_3,
        startHealth = 50,
        minAttackDefenseValue = 5,
        maxAttackDefenseValue = 7
    ),
    ENEMY_4(
        lvl = 3,
        titleRes = Res.string.enemy_4_name,
        imageRes = Res.drawable.enemy_picture_4,
        startHealth = 60,
        minAttackDefenseValue = 7,
        maxAttackDefenseValue = 9
    ),
    ENEMY_5(
        lvl = 4,
        titleRes = Res.string.enemy_5_name,
        imageRes = Res.drawable.enemy_picture_5,
        startHealth = 75,
        minAttackDefenseValue = 9,
        maxAttackDefenseValue = 11
    ),
}