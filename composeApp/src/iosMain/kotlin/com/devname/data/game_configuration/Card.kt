package com.devname.data.game_configuration

import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.attack_card_1_desc
import jokersclutch.composeapp.generated.resources.attack_card_1_name
import jokersclutch.composeapp.generated.resources.attack_card_2_desc
import jokersclutch.composeapp.generated.resources.attack_card_2_name
import jokersclutch.composeapp.generated.resources.attack_card_3_desc
import jokersclutch.composeapp.generated.resources.attack_card_3_name
import jokersclutch.composeapp.generated.resources.card_art_example
import jokersclutch.composeapp.generated.resources.defense_card_1_desc
import jokersclutch.composeapp.generated.resources.defense_card_1_name
import jokersclutch.composeapp.generated.resources.defense_card_2_desc
import jokersclutch.composeapp.generated.resources.defense_card_2_name
import jokersclutch.composeapp.generated.resources.defense_card_3_desc
import jokersclutch.composeapp.generated.resources.defense_card_3_name
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
    val descriptionRes: StringResource,
    val energyCost: Int,
    val damage: Int = 0,
    val block: Int = 0,
    val drawCards: Int = 0,
    val gainEnergy: Int = 0,
    val loseHealth: Int = 0,
) {
    ATTACK_CARD_1(
        titleRes = Res.string.attack_card_1_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.attack_card_1_desc,
        energyCost = 1,
        damage = 2,
    ),
    ATTACK_CARD_2(
        titleRes = Res.string.attack_card_2_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.attack_card_2_desc,
        energyCost = 2,
        damage = 5,
    ),
    ATTACK_CARD_3(
        titleRes = Res.string.attack_card_3_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.attack_card_3_desc,
        energyCost = 3,
        damage = 8,
    ),
    DEFENSE_CARD_1(
        titleRes = Res.string.defense_card_1_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.defense_card_1_desc,
        energyCost = 1,
        block = 1,
    ),
    DEFENSE_CARD_2(
        titleRes = Res.string.defense_card_2_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.defense_card_2_desc,
        energyCost = 1,
        block = 3,
    ),
    DEFENSE_CARD_3(
        titleRes = Res.string.defense_card_3_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.defense_card_3_desc,
        energyCost = 1,
        block = 6,
    ),
    MISC_CARD_1(
        titleRes = Res.string.mis_card_1_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.mis_card_1_desc,
        energyCost = 0,
        gainEnergy = 1,
    ),
    MISC_CARD_2(
        titleRes = Res.string.mis_card_2_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.mis_card_2_desc,
        energyCost = 0,
        drawCards = 1,
    ),
    MISC_CARD_3(
        titleRes = Res.string.mis_card_3_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.mis_card_3_desc,
        energyCost = 2,
        damage = 2,
        block = 2,
    ),
    MISC_CARD_4(
        titleRes = Res.string.mis_card_4_name,
        imageRes = Res.drawable.card_art_example,
        descriptionRes = Res.string.mis_card_4_desc,
        energyCost = 0,
        damage = 5,
        loseHealth = 5,
    ),
}