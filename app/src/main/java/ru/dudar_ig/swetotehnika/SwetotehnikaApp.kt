package ru.dudar_ig.swetotehnika

import android.app.Application
import ru.dudar_ig.swetotehnika.database.ProductDbRepo

class SwetotehnikaApp: Application() {
    override fun onCreate() {
        super.onCreate()
        ProductDbRepo.initialize(this)
    }
}