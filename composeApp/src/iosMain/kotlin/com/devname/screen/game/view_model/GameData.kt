package com.devname.screen.game.view_model

import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.Enemy
import com.devname.data.game_configuration.PlayerStats

data class GameData(
    // Player Stats
    val playerHealth: Int = PlayerStats.START_HEALTH,
    val startDeck: List<Card> = emptyList(),
    val playerDeck: List<Card> = emptyList(),
    val playerHand: List<Card> = emptyList(),
    val playerEnergy: Int = PlayerStats.START_ENERGY,
    val playerAttack: Int = 0,
    val playerBlock: Int = 0,

    // Controls
    val selectedCardIndex: Int? = null,
    val isTurnEnded: Boolean = false,
    val displayHandStartIndex: Int = 0,
    val displayCard: Card? = null,

    // Enemy Stats
    val enemyHealth: Int = Enemy.ENEMY_1.startHealth,
    val enemy: Enemy = Enemy.ENEMY_1,
    val enemyAttack: Int = 0,
    val enemyBlock: Int = 0,
)
