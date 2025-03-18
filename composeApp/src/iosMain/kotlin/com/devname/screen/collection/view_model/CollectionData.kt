package com.devname.screen.collection.view_model

import com.devname.data.game_configuration.Card

data class CollectionData(
    val lastCompletedLvl: Int = -1,
    val displayCard: Card? = null,
)
