package ru.dudar_ig.swetotehnika

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import ru.dudar_ig.swetotehnika.database.ProductDbRepo

class SwetotehnikaApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("c9e89c39-2176-4ab1-bc20-71cf3dec7575")
        ProductDbRepo.initialize(this)
    }
}