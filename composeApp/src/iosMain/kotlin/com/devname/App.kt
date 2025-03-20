package com.devname

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.devname.di.appModule
import com.devname.navigation.Navigation
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            Navigation()
        }
    }
}