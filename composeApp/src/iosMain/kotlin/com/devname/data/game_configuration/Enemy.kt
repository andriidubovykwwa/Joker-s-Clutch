package com.devname.data.game_configuration

import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.card_art_example
import jokersclutch.composeapp.generated.resources.enemy_1_name
import jokersclutch.composeapp.generated.resources.enemy_2_name
import jokersclutch.composeapp.generated.resources.enemy_3_name
import jokersclutch.composeapp.generated.resources.enemy_4_name
import jokersclutch.composeapp.generated.resources.enemy_5_name
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Enemy(
    val titleRes: StringResource,
    val imageRes: DrawableResource,
    val startHealth: Int,
    val minAttackDefenseValue: Int,
    val maxAttackDefenseValue: Int
) {
    ENEMY_1(
        titleRes = Res.string.enemy_1_name,
        imageRes = Res.drawable.card_art_example,
        startHealth = 25,
        minAttackDefenseValue = 2,
        maxAttackDefenseValue = 3
    ),
    ENEMY_2(
        titleRes = Res.string.enemy_2_name,
        imageRes = Res.drawable.card_art_example,
        startHealth = 50,
        minAttackDefenseValue = 3,
        maxAttackDefenseValue = 5
    ),
    ENEMY_3(
        titleRes = Res.string.enemy_3_name,
        imageRes = Res.drawable.card_art_example,
        startHealth = 75,
        minAttackDefenseValue = 5,
        maxAttackDefenseValue = 8
    ),
    ENEMY_4(
        titleRes = Res.string.enemy_4_name,
        imageRes = Res.drawable.card_art_example,
        startHealth = 100,
        minAttackDefenseValue = 8,
        maxAttackDefenseValue = 10
    ),
    ENEMY_5(
        titleRes = Res.string.enemy_5_name,
        imageRes = Res.drawable.card_art_example,
        startHealth = 150,
        minAttackDefenseValue = 10,
        maxAttackDefenseValue = 12
    ),
}