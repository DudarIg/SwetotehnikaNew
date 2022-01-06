package ru.dudar_ig.swetotehnika.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.dudar_ig.swetotehnika.ui.catalog.MainFragment
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navLists = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()



        val mainFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (mainFragment == null) {
            binding.toolbarTitle.text = resources.getString(R.string.app_name)
            val fragment = OneFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    fun navClickable(iters: Int) {
        (0..3).forEach {
            navLists[it].isClickable = if (it == iters)  false else true
        }
        if (iters == -1) binding.toolbarTitle.text = resources.getString(R.string.app_name)
    }

    fun titleText(text: String) {
        binding.toolbarTitle.text = text
    }



    private fun initBottomNav() {
        navLists.add(findViewById(R.id.nav_catalog))
        navLists.add(findViewById(R.id.nav_zakaz))
        navLists.add(findViewById(R.id.nav_price))
        navLists.add(findViewById(R.id.nav_contact))

        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment : Fragment
            when (item.itemId) {
                R.id.nav_catalog -> {
                    binding.toolbarTitle.text = resources.getString(R.string.cat_name)
                    fragment = MainFragment.newInstance()
                    navClickable(0)
                }
                R.id.nav_zakaz -> {
                    Toast.makeText(this, "Выбран заказ", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                   navClickable(1)
                }
                R.id.nav_price -> {
                    Toast.makeText(this, "Выбран прайс", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    navClickable(2)
                }
                R.id.nav_contact -> {
                    Toast.makeText(this, "Выбран контакт", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    navClickable(3)
                }
                else -> {
                    fragment = MainFragment.newInstance()
                }
            }
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

            true
        }
    }
}