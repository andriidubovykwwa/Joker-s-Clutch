package com.devname.di

import com.devname.screen.game.view_model.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GameViewModel(get()) }
}
