package com.devname.data.game_configuration

import androidx.compose.ui.graphics.Color
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.art_attack_card_1
import jokersclutch.composeapp.generated.resources.art_attack_card_2
import jokersclutch.composeapp.generated.resources.art_attack_card_3
import jokersclutch.composeapp.generated.resources.art_defense_card_1
import jokersclutch.composeapp.generated.resources.art_defense_card_2
import jokersclutch.composeapp.generated.resources.art_defense_card_3
import jokersclutch.composeapp.generated.resources.art_misc_card_1
import jokersclutch.composeapp.generated.resources.art_misc_card_2
import jokersclutch.composeapp.generated.resources.art_misc_card_3
import jokersclutch.composeapp.generated.resources.art_misc_card_4
import jokersclutch.composeapp.generated.resources.attack_card_1_desc
import jokersclutch.composeapp.generated.resources.attack_card_1_name
import jokersclutch.composeapp.generated.resources.attack_card_2_desc
import jokersclutch.composeapp.generated.resources.attack_card_2_name
import jokersclutch.composeapp.generated.resources.attack_card_3_desc
import jokersclutch.composeapp.generated.resources.attack_card_3_name
import jokersclutch.composeapp.generated.resources.defense_card_1_desc
import jokersclutch.composeapp.generated.resources.defense_card_1_name
import jokersclutch.composeapp.generated.resources.defense_card_2_desc
import jokersclutch.composeapp.generated.resources.defense_card_2_name
import jokersclutch.composeapp.generated.resources.defense_card_3_desc
import jokersclutch.composeapp.generated.resources.defense_card_3_name
import jokersclutch.composeapp.generated.resources.icon_attack_card_1
import jokersclutch.composeapp.generated.resources.icon_attack_card_2
import jokersclutch.composeapp.generated.resources.icon_attack_card_3
import jokersclutch.composeapp.generated.resources.icon_defense_card_1
import jokersclutch.composeapp.generated.resources.icon_defense_card_2
import jokersclutch.composeapp.generated.resources.icon_defense_card_3
import jokersclutch.composeapp.generated.resources.icon_misc_card_1
import jokersclutch.composeapp.generated.resources.icon_misc_card_2
import jokersclutch.composeapp.generated.resources.icon_misc_card_3
import jokersclutch.composeapp.generated.resources.icon_misc_card_4
import jokersclutch.composeapp.generated.resources.mis_card_1_desc
import jokersclutch.composeapp.generated.resources.mis_card_1_name
import jokersclutch.composeapp.generated.resources.mis_card_2_desc
import jokersclutch.composeapp.generated.resources.mis_card_2_name
import jokersclutch.composeapp.generated.resources.mis_card_3_desc
import jokersclutch.composeapp.generated.resources.mis_card_3_name
import jokersclutch.composeapp.generated.resources.mis_card_4_desc
import jokersclutch.composeapp.generated.resources.mis_card_4_name
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Card(
    val titleRes: StringResource,
    val imageRes: DrawableResource,
    val iconRes: DrawableResource,
    val descriptionRes: StringResource,
    val borderColor: Color,
    val energyCost: Int,
    val damage: Int = 0,
    val block: Int = 0,
    val drawCards: Int = 0,
    val gainEnergy: Int = 0,
    val loseHealth: Int = 0,
    val lvlToUnlock: Int = -1,
) {
    ATTACK_CARD_1(
        titleRes = Res.string.attack_card_1_name,
        imageRes = Res.drawable.art_attack_card_1,
        iconRes = Res.drawable.icon_attack_card_1,
        descriptionRes = Res.string.attack_card_1_desc,
        borderColor = Color(0xffFF0000),
        energyCost = 1,
        damage = 2,
    ),
    ATTACK_CARD_2(
        titleRes = Res.string.attack_card_2_name,
        imageRes = Res.drawable.art_attack_card_2,
        iconRes = Res.drawable.icon_attack_card_2,
        descriptionRes = Res.string.attack_card_2_desc,
        borderColor = Color(0xffFF0000),
        energyCost = 2,
        damage = 5,
    ),
    ATTACK_CARD_3(
        titleRes = Res.string.attack_card_3_name,
        imageRes = Res.drawable.art_attack_card_3,
        iconRes = Res.drawable.icon_attack_card_3,
        descriptionRes = Res.string.attack_card_3_desc,
        borderColor = Color(0xffFF0000),
        energyCost = 3,
        damage = 8,
    ),
    DEFENSE_CARD_1(
        titleRes = Res.string.defense_card_1_name,
        imageRes = Res.drawable.art_defense_card_1,
        iconRes = Res.drawable.icon_defense_card_1,
        descriptionRes = Res.string.defense_card_1_desc,
        borderColor = Color(0xffFFAA00),
        energyCost = 1,
        block = 1,
    ),
    DEFENSE_CARD_2(
        titleRes = Res.string.defense_card_2_name,
        imageRes = Res.drawable.art_defense_card_2,
        iconRes = Res.drawable.icon_defense_card_2,
        descriptionRes = Res.string.defense_card_2_desc,
        borderColor = Color(0xffFFAA00),
        energyCost = 2,
        block = 3,
    ),
    DEFENSE_CARD_3(
        titleRes = Res.string.defense_card_3_name,
        imageRes = Res.drawable.art_defense_card_3,
        iconRes = Res.drawable.icon_defense_card_3,
        descriptionRes = Res.string.defense_card_3_desc,
        borderColor = Color(0xffFFAA00),
        energyCost = 3,
        block = 6,
        lvlToUnlock = 0
    ),
    MISC_CARD_1(
        titleRes = Res.string.mis_card_1_name,
        imageRes = Res.drawable.art_misc_card_1,
        iconRes = Res.drawable.icon_misc_card_1,
        descriptionRes = Res.string.mis_card_1_desc,
        borderColor = Color(0xff00EEFF),
        energyCost = 0,
        gainEnergy = 1,
        lvlToUnlock = 1
    ),
    MISC_CARD_2(
        titleRes = Res.string.mis_card_2_name,
        imageRes = Res.drawable.art_misc_card_2,
        iconRes = Res.drawable.icon_misc_card_2,
        descriptionRes = Res.string.mis_card_2_desc,
        borderColor = Color(0xff00FF04),
        energyCost = 0,
        drawCards = 1,
        lvlToUnlock = 2
    ),
    MISC_CARD_3(
        titleRes = Res.string.mis_card_3_name,
        imageRes = Res.drawable.art_misc_card_3,
        iconRes = Res.drawable.icon_misc_card_3,
        descriptionRes = Res.string.mis_card_3_desc,
        borderColor = Color(0xffFF00A1),
        energyCost = 2,
        damage = 2,
        block = 2,
        lvlToUnlock = 3
    ),
    MISC_CARD_4(
        titleRes = Res.string.mis_card_4_name,
        imageRes = Res.drawable.art_misc_card_4,
        iconRes = Res.drawable.icon_misc_card_4,
        descriptionRes = Res.string.mis_card_4_desc,
        borderColor = Color(0xffFF0000),
        energyCost = 0,
        damage = 5,
        loseHealth = 5,
        lvlToUnlock = 4
    ),
}