package ru.dudar_ig.swetotehnika.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.database.CartCountVM
import ru.dudar_ig.swetotehnika.databinding.ActivityMainBinding
import ru.dudar_ig.swetotehnika.ui.catalog.ListTovarFragment
import ru.dudar_ig.swetotehnika.ui.catalog.MainFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var searchIsVisible = false
    var navLists = mutableListOf<View>()
    private val cartCounts by viewModels<CartCountVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Swetotehnika)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val internet = isNetworkConnect(this)
        if (!internet) {
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("Интернет")
                setMessage("Отсутствует соединение!")
                setIcon(R.drawable.ic_wifi_off)
                setPositiveButton(
                    "Ok"
                ) { dialog, id ->
                    val filmsDbRepo = FilmsDbRepo.get()
                    filmsDbRepo.deleteFilm(film)
                }
                setNegativeButton(
                    "Cancel"
                ) { dialog, id ->
                }
            }.show()
        }

        initBottomNav()

        binding.imageView.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transmis, Slide(Gravity.TOP))
            searchIsVisible = !searchIsVisible
            binding.searchLayout.visibility = if (searchIsVisible) View.VISIBLE else View.GONE
        }

        binding.searchLayout.setEndIconOnClickListener {

            closeKeyboard()

            val searchOld = binding.inputText.text.toString().trim()
            KatId.search = searchOld.replace(
                ' ', // old char
                '_', // new char
                true // ignore case Boolean = false
            )
            val fragment = ListTovarFragment.newInstance(0, "Поиск: <${KatId.search}>")
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                fragment).addToBackStack(null).commit()
        }

        val mainFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (mainFragment == null) {
            binding.toolbarTitle.text = resources.getString(R.string.name0)
            navLists[0].isClickable = false
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
    }

    fun titleText(text: String) {
        binding.toolbarTitle.text = text
    }

    private fun initBottomNav() {
        navLists.add(findViewById(R.id.nav_home))
        navLists.add(findViewById(R.id.nav_catalog))
        navLists.add(findViewById(R.id.nav_zakaz))
        navLists.add(findViewById(R.id.nav_contact))

        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment : Fragment
            when (item.itemId) {
                R.id.nav_home -> {
                    navClickable(0)
                    fragment = OneFragment.newInstance()
                    startFragment(fragment)
                }
                R.id.nav_catalog -> {
                    navClickable(1)
                    binding.toolbarTitle.text = resources.getString(R.string.cat_name)
                    fragment = MainFragment.newInstance()
                    startFragment(fragment)
                }
                R.id.nav_zakaz -> {
                    navClickable(2)
                    fragment = CartFragment.newInstance("Ваш заказ:")
                    startFragment(fragment)
                }

                R.id.nav_contact -> {
                    navClickable(3)
                    fragment = ContactFragment.newInstance("Контакты")
                    startFragment(fragment)

                }
                else -> {
                    fragment = MainFragment.newInstance()
                    startFragment(fragment)
                }
            }

            true
        }
    }

    fun startFragment(f: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, f)
            .addToBackStack(null)
            .commit()
    }

    fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    override fun onResume() {
        super.onResume()
        cartCounts.cartCount.observe(this, Observer {
            if (it == 0) {
                binding.bottomNav.removeBadge(R.id.nav_zakaz)
            } else {
                binding.bottomNav.getOrCreateBadge(R.id.nav_zakaz)
            }
        })
    }

    fun isNetworkConnect(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }


}