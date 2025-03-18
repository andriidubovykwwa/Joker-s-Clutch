package com.devname.screen.collection.view_model

import com.devname.data.game_configuration.Card

interface CollectionEvent {
    data class DisplayCard(val card: Card?) : CollectionEvent
}