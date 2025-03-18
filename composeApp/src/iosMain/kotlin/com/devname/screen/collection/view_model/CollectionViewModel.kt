package com.devname.screen.collection.view_model

import androidx.lifecycle.ViewModel
import com.devname.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CollectionViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CollectionData())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(lastCompletedLvl = appRepository.getLastCompletedLvl())
        }
    }
}