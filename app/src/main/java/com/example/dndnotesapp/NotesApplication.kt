package com.example.dndnotesapp

import android.app.Application
import com.example.dndnotesapp.data.AppContainer
import com.example.dndnotesapp.data.AppDataContainer

class NotesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}