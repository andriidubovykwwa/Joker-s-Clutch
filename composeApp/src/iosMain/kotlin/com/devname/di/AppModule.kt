package com.devname.di

import com.devname.data.repository.AppRepository
import com.devname.data.repository.DefaultAppRepository
import com.devname.screen.collection.view_model.CollectionViewModel
import com.devname.screen.game.view_model.GameViewModel
import com.devname.screen.level_selector.view_model.LevelSelectorViewModel
import com.devname.screen.menu.view_model.MenuViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppRepository> { DefaultAppRepository() }
    viewModel { GameViewModel(get(), get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { LevelSelectorViewModel(get()) }
    viewModel { CollectionViewModel(get()) }
}
