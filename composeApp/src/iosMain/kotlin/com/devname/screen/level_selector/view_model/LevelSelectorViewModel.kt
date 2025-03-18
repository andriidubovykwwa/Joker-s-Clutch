package com.devname.screen.level_selector.view_model

import androidx.lifecycle.ViewModel
import com.devname.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LevelSelectorViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LevelSelectorData())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(lastCompletedLvl = appRepository.getLastCompletedLvl())
        }
    }
}