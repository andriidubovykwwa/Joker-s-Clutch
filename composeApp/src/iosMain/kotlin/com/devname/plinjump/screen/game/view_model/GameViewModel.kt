package com.devname.plinjump.screen.game.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.plinjump.data.game_configuration.Card
import com.devname.plinjump.data.game_configuration.DisplayInfo
import com.devname.plinjump.data.game_configuration.PlayerStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.min

class GameViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameData())
    val state = _state.asStateFlow()

    init {
        // TODO: only unlocked cards
        val startDeck = Card.entries.flatMap { listOf(it, it) }.shuffled()
        val enemyAttackDefense =
            (state.value.enemy.minAttackDefenseValue..state.value.enemy.maxAttackDefenseValue).random()
        val enemyAttack = (0..enemyAttackDefense).random()
        val enemyBlock = enemyAttackDefense - enemyAttack
        _state.update {
            it.copy(
                startDeck = startDeck,
                playerDeck = startDeck.drop(PlayerStats.DRAW_CARD_PER_TURN),
                playerHand = startDeck.take(PlayerStats.DRAW_CARD_PER_TURN),
                enemyAttack = enemyAttack,
                enemyBlock = enemyBlock
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
        _state.update { it.copy(isTurnEnded = true) }
        // TODO: calculate new values for player and enemy health
        // TODO: check victory/defeat condition

        // TODO: after animations set new values for enemy shield and attack
    }

    private fun processPlaySelectedCard() = viewModelScope.launch {
        val index = state.value.selectedCardIndex ?: return@launch // No card selected
        val selectedCard = state.value.playerHand.getOrNull(index)
        val card = selectedCard ?: return@launch // No card selected
        if (state.value.playerEnergy < card.energyCost) return@launch // Not enough energy
        if (state.value.playerHealth < card.loseHealth) return@launch // Not enough health
        val displayIndex = state.value.displayHandStartIndex
        _state.update {
            it.copy(
                selectedCardIndex = null,
                playerHand = state.value.playerHand.filterIndexed { i, _ -> i != index },
                displayHandStartIndex = if (displayIndex > 0 && displayIndex + DisplayInfo.CARD_IN_HAND_MAX >= it.playerHand.size) displayIndex - 1
                else displayIndex
            )
        }
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