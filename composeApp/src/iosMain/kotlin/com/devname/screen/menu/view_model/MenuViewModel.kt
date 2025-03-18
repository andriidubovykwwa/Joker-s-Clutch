package com.devname.screen.menu.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MenuData())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    music = appRepository.getMusic(),
                    sounds = appRepository.getSounds()
                )
            }
        }
    }

    fun obtainEvent(event: MenuEvent) {
        when (event) {
            is MenuEvent.SetMusic -> processSetMusic(event.value)
            is MenuEvent.SetSounds -> processSetSounds(event.value)
        }
    }

    private fun processSetMusic(value: Int) = viewModelScope.launch {
        appRepository.setMusic(value)
        _state.update {
            it.copy(
                music = value.coerceIn(
                    AppRepository.MIN_MUSIC_VALUE,
                    AppRepository.MAX_MUSIC_VALUE
                )
            )
        }
    }

    private fun processSetSounds(value: Int) = viewModelScope.launch {
        appRepository.setSounds(value)
        _state.update {
            it.copy(
                sounds = value.coerceIn(
                    AppRepository.MIN_SOUNDS_VALUE,
                    AppRepository.MAX_SOUNDS_VALUE
                )
            )
        }
    }
}