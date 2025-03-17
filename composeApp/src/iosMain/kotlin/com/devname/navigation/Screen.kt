package com.devname.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Menu : Screen

    @Serializable
    data object Info : Screen

    @Serializable
    data object LevelSelector : Screen

    @Serializable
    data object Collection : Screen

    @Serializable
    data class Game(val lvl: Int) : Screen
}