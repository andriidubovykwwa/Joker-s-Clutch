package com.devname.screen.info.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.data.game_configuration.Card
import com.devname.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InfoViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(InfoData())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(sounds = appRepository.getSounds())
            }
        }
    }

    fun obtainEvent(event: InfoEvent) {
        when (event) {
            is InfoEvent.DisplayCard -> processDisplayCard(event.card)
        }
    }

    private fun processDisplayCard(card: Card?) = viewModelScope.launch {
        _state.update { it.copy(displayCard = card) }
    }
}