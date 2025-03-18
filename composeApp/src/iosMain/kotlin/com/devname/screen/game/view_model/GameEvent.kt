package com.devname.screen.game.view_model

import com.devname.data.game_configuration.Card

sealed interface GameEvent {
    data class SelectCard(val index: Int) : GameEvent
    data class DisplayCard(val card: Card?) : GameEvent
    data object Restart : GameEvent
    data object PlaySelectedCard : GameEvent
    data object SwipeHandLeft : GameEvent
    data object SwipeHandRight : GameEvent
    data object SetupNewTurn : GameEvent
    data object EndTurn : GameEvent
}