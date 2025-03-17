package com.devname.plinjump.di

import com.devname.plinjump.screen.game.view_model.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GameViewModel() }
}
