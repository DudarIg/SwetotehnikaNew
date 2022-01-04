package ru.dudar_ig.swetotehnika

import android.content.ClipData
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import ru.dudar_ig.swetotehnika.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

  private  lateinit var navLists: MutableList<View>
   // private lateinit var navHome: View
    private lateinit var navCatalog: View
    private lateinit var navZakaz: View
    private lateinit var navPrice: View
    private lateinit var navContact: View




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()

        val mainFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (mainFragment == null) {

           // navLists[0].isClickable = false
            val fragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }

    }

    private fun navClickable(iters: Int) {
        (0..3).forEach {
            navLists[it].isClickable = if (it == iters)  false else true
        }
    }

    private fun initBottomNav() {

       // navHome = findViewById(R.id.nav_home)
        navCatalog = findViewById(R.id.nav_catalog)
        navZakaz = findViewById(R.id.nav_zakaz)
        navPrice = findViewById(R.id.nav_price)
        navContact = findViewById(R.id.nav_contact)

        navLists = mutableListOf( navCatalog, navZakaz, navPrice, navContact)


        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment : Fragment
            when (item.itemId) {
//                R.id.nav_home -> {
//                    fragment = MainFragment.newInstance()
//                   // navLists[0].isClickable = false
//                    navClickable(0)
//
//                }
                R.id.nav_catalog -> {
                    Toast.makeText(this, "Выбран каталог", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    //navLists[1].isClickable = false
                    navClickable(1)

                }
                R.id.nav_zakaz -> {
                    Toast.makeText(this, "Выбран заказ", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    //navLists[2].isClickable = false
                   navClickable(2)
                }
                R.id.nav_price -> {
                    Toast.makeText(this, "Выбран прайс", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    //navLists[3].isClickable = false
                    navClickable(3)
                }
                R.id.nav_contact -> {
                    Toast.makeText(this, "Выбран контакт", Toast.LENGTH_SHORT).show()
                    fragment = MainFragment.newInstance()
                    //navLists[4].isClickable = false
                    navClickable(4)
                }
                else -> {
//                    intent = Intent(this,GlobusActivity::class.java )
//                    startActivity(intent)
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