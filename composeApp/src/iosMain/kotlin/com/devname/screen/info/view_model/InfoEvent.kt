package com.devname.screen.info.view_model

import com.devname.data.game_configuration.Card

sealed interface InfoEvent {
    data class DisplayCard(val card: Card?) : InfoEvent
}