package com.devname.screen.game.view_model

sealed interface GameEvent {
    data class SelectCard(val index: Int) : GameEvent
    data object Restart : GameEvent
    data object PlaySelectedCard : GameEvent
    data object SwipeHandLeft : GameEvent
    data object SwipeHandRight : GameEvent
    data object SetupNewTurn : GameEvent
    data object EndTurn : GameEvent
}