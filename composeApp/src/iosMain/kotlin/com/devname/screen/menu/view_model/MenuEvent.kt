package com.devname.screen.menu.view_model

sealed interface MenuEvent {
    data class SetMusic(val value: Int) : MenuEvent
    data class SetSounds(val value: Int) : MenuEvent
}