package com.devname.screen.game.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.devname.data.game_configuration.Card
import com.devname.data.game_configuration.DisplayInfo
import com.devname.data.game_configuration.Enemy
import com.devname.data.game_configuration.PlayerStats
import com.devname.data.repository.AppRepository
import com.devname.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.min

class GameViewModel(
    savedStateHandle: SavedStateHandle,
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(GameData())
    val state = _state.asStateFlow()

    private val _animatingCardIndex = MutableStateFlow<Int?>(null)
    val animatingCardIndex = _animatingCardIndex.asStateFlow()

    init {
        val lvl = savedStateHandle.toRoute<Screen.Game>().lvl

        val enemy = Enemy.entries.getOrNull(lvl) ?: Enemy.ENEMY_1
        val lastCompletedLvl = appRepository.getLastCompletedLvl()
        val startDeck =
            Card.entries
                .filter { lastCompletedLvl >= it.lvlToUnlock }
                .flatMap { card -> List(PlayerStats.COPIES_OF_EACH_CARD_IN_START_DECK) { card } }
                .shuffled()
        val enemyAttackDefense =
            (enemy.minAttackDefenseValue..enemy.maxAttackDefenseValue).random()
        val enemyAttack = (0..enemyAttackDefense).random()
        val enemyBlock = enemyAttackDefense - enemyAttack
        _state.update {
            it.copy(
                startDeck = startDeck,
                enemy = enemy,
                enemyHealth = enemy.startHealth,
                playerDeck = startDeck.drop(PlayerStats.DRAW_CARD_PER_TURN),
                playerHand = startDeck.take(PlayerStats.DRAW_CARD_PER_TURN),
                enemyAttack = enemyAttack,
                enemyBlock = enemyBlock,
                lastCompletedLvl = lastCompletedLvl
            )
        }


    }

    fun obtainEvent(event: GameEvent) {
        when (event) {
            is GameEvent.EndTurn -> processEndTurn()
            is GameEvent.PlaySelectedCard -> processPlaySelectedCard()
            is GameEvent.SelectCard -> processSelectCard(event.index)
            is GameEvent.SwipeHandLeft -> processSwipeHandLeft()
            is GameEvent.SwipeHandRight -> processSwipeHandRight()
            is GameEvent.SetupNewTurn -> processSetupNewTurn()
            is GameEvent.Restart -> processOnRestart()
            is GameEvent.DisplayCard -> processDisplayCard(event.card)
        }
    }

    private fun processDisplayCard(card: Card?) = viewModelScope.launch {
        _state.update { it.copy(displayCard = card) }
    }

    private fun processOnRestart() = viewModelScope.launch {
        val startDeck =
            Card.entries
                .filter { state.value.lastCompletedLvl >= it.lvlToUnlock }
                .flatMap { card -> List(PlayerStats.COPIES_OF_EACH_CARD_IN_START_DECK) { card } }
                .shuffled()
        val enemy = state.value.enemy
        val enemyAttackDefense =
            (enemy.minAttackDefenseValue..enemy.maxAttackDefenseValue).random()
        val enemyAttack = (0..enemyAttackDefense).random()
        val enemyBlock = enemyAttackDefense - enemyAttack
        _state.update {
            it.copy(
                enemyHealth = enemy.startHealth,
                playerHealth = PlayerStats.START_HEALTH,
                isTurnEnded = false,
                startDeck = startDeck,
                playerDeck = startDeck.drop(PlayerStats.DRAW_CARD_PER_TURN),
                playerHand = startDeck.take(PlayerStats.DRAW_CARD_PER_TURN),
                enemyAttack = enemyAttack,
                enemyBlock = enemyBlock,
                playerAttack = 0,
                playerBlock = 0,
                displayHandStartIndex = 0,
                selectedCardIndex = null,
                newCardUnlocked = false
            )
        }
    }

    private fun processSwipeHandLeft() = viewModelScope.launch {
        _state.update {
            it.copy(displayHandStartIndex = maxOf(it.displayHandStartIndex - 1, 0))
        }
    }

    private fun processSwipeHandRight() = viewModelScope.launch {
        _state.update {
            it.copy(
                displayHandStartIndex = min(
                    it.displayHandStartIndex + 1,
                    it.playerHand.size - DisplayInfo.CARD_IN_HAND_MAX
                )
            )
        }
    }

    private fun processEndTurn() = viewModelScope.launch {
        val actualPlayerAttack = maxOf(state.value.playerAttack - state.value.enemyBlock, 0)
        val actualEnemyAttack = maxOf(state.value.enemyAttack - state.value.playerBlock, 0)
        val playerHealth = maxOf(state.value.playerHealth - actualEnemyAttack, 0)
        val enemyHealth = maxOf(state.value.enemyHealth - actualPlayerAttack, 0)
        if (playerHealth > 0 && enemyHealth == 0) {
            val completedLvl = state.value.enemy.lvl
            val maxLvl = state.value.lastCompletedLvl
            appRepository.processCompletedLvl(state.value.enemy.lvl)
            if (completedLvl > maxLvl) {
                _state.update {
                    it.copy(
                        lastCompletedLvl = completedLvl,
                        newCardUnlocked = true,
                    )
                }
            }
        }
        _state.update {
            it.copy(playerHealth = playerHealth, enemyHealth = enemyHealth, isTurnEnded = true)
        }
    }

    private fun processSetupNewTurn() = viewModelScope.launch {
        val enemyAttackDefense =
            (state.value.enemy.minAttackDefenseValue..state.value.enemy.maxAttackDefenseValue).random()
        val enemyAttack = (0..enemyAttackDefense).random()
        val enemyBlock = enemyAttackDefense - enemyAttack
        val startDeck = state.value.startDeck.shuffled()
        _state.update {
            it.copy(
                isTurnEnded = false,
                playerEnergy = PlayerStats.START_ENERGY,
                startDeck = startDeck,
                playerDeck = startDeck.drop(PlayerStats.DRAW_CARD_PER_TURN),
                playerHand = startDeck.take(PlayerStats.DRAW_CARD_PER_TURN),
                enemyAttack = enemyAttack,
                enemyBlock = enemyBlock,
                playerAttack = 0,
                playerBlock = 0,
                displayHandStartIndex = 0,
                selectedCardIndex = null
            )
        }
    }

    private fun processPlaySelectedCard() = viewModelScope.launch {
        val index = state.value.selectedCardIndex ?: return@launch // No card selected
        val selectedCard = state.value.playerHand.getOrNull(index)
        val card = selectedCard ?: return@launch // No card selected
        if (state.value.playerEnergy < card.energyCost) return@launch // Not enough energy
        if (state.value.playerHealth < card.loseHealth) return@launch // Not enough health
        val displayIndex = state.value.displayHandStartIndex

        _animatingCardIndex.update { index } // Start animation
        delay(DisplayInfo.PLAY_CARD_ANIMATION_TIME) // Wait till animation ends
        _state.update {
            it.copy(
                selectedCardIndex = null,
                playerHand = state.value.playerHand.filterIndexed { i, _ -> i != index },
                displayHandStartIndex = if (displayIndex > 0 && displayIndex + DisplayInfo.CARD_IN_HAND_MAX >= it.playerHand.size) displayIndex - 1
                else displayIndex
            )
        }
        _animatingCardIndex.update { null } // Reset animation
        // Apply card effects
        _state.update {
            it.copy(
                playerAttack = it.playerAttack + card.damage,
                playerBlock = it.playerBlock + card.block,
                playerEnergy = it.playerEnergy - card.energyCost + card.gainEnergy,
                playerHealth = it.playerHealth - card.loseHealth,
                playerHand = if (card.drawCards == 0) it.playerHand
                else it.playerHand + it.playerDeck.take(card.drawCards),
                playerDeck = if (card.drawCards == 0) it.playerDeck
                else it.playerDeck.drop(card.drawCards)
            )
        }
    }

    private fun processSelectCard(index: Int) = viewModelScope.launch {
        if (state.value.selectedCardIndex != index) {
            _state.update { it.copy(selectedCardIndex = index) }
        } else {
            _state.update { it.copy(selectedCardIndex = null) }
        }
    }
}