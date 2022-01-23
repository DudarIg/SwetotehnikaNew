package ru.dudar_ig.swetotehnika.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.chip.Chip
import ru.dudar_ig.swetotehnika.R

class NoInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        val closeChip = findViewById<Chip>(R.id.close_chip)
        closeChip.setOnClickListener {
            finishAffinity()
        }
    }
}