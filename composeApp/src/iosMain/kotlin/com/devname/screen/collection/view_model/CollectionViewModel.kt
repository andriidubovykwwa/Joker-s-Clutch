package com.devname.screen.collection.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.data.game_configuration.Card
import com.devname.data.repository.AppRepository
import com.devname.utils.SoundController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CollectionViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CollectionData())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    lastCompletedLvl = appRepository.getLastCompletedLvl(),
                    sounds = appRepository.getSounds()
                )
            }
        }
    }

    fun obtainEvent(event: CollectionEvent) {
        when (event) {
            is CollectionEvent.DisplayCard -> processDisplayCard(event.card)
        }
    }

    private fun processDisplayCard(card: Card?) = viewModelScope.launch {
        SoundController.playClick(state.value.sounds)
        _state.update { it.copy(displayCard = card) }
    }
}