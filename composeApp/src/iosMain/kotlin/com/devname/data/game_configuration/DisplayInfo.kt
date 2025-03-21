package com.devname.data.game_configuration

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally

object DisplayInfo {
    const val CARD_IN_HAND_MAX = 3
    const val CARD_IN_ROW_MAX_COLLECTION = 3
    const val PLAY_CARD_ANIMATION_TIME = 900L
    const val SHUFFLE_ANIMATION_TIME = 1000L

    const val SELECTED_CARD_SCALE = 1.04f
    const val SELECTED_CARD_ROTATION = 7f

    const val SCREEN_ELEMENTS_ANIMATION_DURATION = 800
    val elementStartAnimation = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(SCREEN_ELEMENTS_ANIMATION_DURATION)
    ) + scaleIn(
        initialScale = 0.5f,
        animationSpec = tween(SCREEN_ELEMENTS_ANIMATION_DURATION)
    )
}