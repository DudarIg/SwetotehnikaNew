package ru.dudar_ig.swetotehnika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dudar_ig.swetotehnika.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}