package ru.dudar_ig.swetotehnika.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.database.CartCountVM
import ru.dudar_ig.swetotehnika.database.ProductDbRepo
import ru.dudar_ig.swetotehnika.databinding.ActivityMainBinding
import ru.dudar_ig.swetotehnika.ui.catalog.ListTovarFragment
import ru.dudar_ig.swetotehnika.ui.catalog.MainFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var searchIsVisible = false
    private var lightIsVisible = false
    var navLists = mutableListOf<View>()
    private val cartCounts by viewModels<CartCountVM>()

    override fun onCreate(savedInstanceState: Bundle?) {

        if (!isNetworkConnect(this)) {
            val intent = Intent(this, NoInternetActivity::class.java)
            startActivity(intent)
        }

        setTheme(R.style.Theme_Swetotehnika)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()
        binding.imageView.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transmis, Slide(Gravity.TOP))
            searchIsVisible = !searchIsVisible
            binding.searchLayout.isVisible = if (searchIsVisible) true else false
        }

        binding.imageLight.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transmis1, Slide(Gravity.TOP))
            lightIsVisible = !lightIsVisible
            binding.cardLight.isVisible = if (lightIsVisible) true else false
        }

        binding.raschetChip.setOnClickListener {
            binding.itogTextview.text = raschet_heigth()
        }

        binding.searchLayout.setEndIconOnClickListener {
            closeKeyboard()
            val searchOld = binding.inputText.text.toString().trim()
            KatId.search = searchOld.replace(
                ' ', // old char
                '_', // new char
                true // ignore case Boolean = false
            )
            val fragment = ListTovarFragment.newInstance(0, "??????????: <${KatId.search}>")
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

    private fun raschet_heigth(): String {
        val k_h = listOf(1.0, 1.2, 1.5, 2.0)
        val k_type = listOf(300, 500, 200, 100, 75, 75, 50, 150, 200, 50, 75, 300, 20, 100)
        if (binding.sEditText.text.toString().trim() == ".") {
            return ""
        }
        if (binding.sEditText.text.toString().trim().length == 0) {
            return ""
        }
        val s : Float = binding.sEditText.text.toString().trim().toFloat()

        val h = k_h[binding.hhSp.selectedItemPosition]
        val type = k_type[binding.spinner.selectedItemPosition]
        val svet = (s*h*type).toInt().toString()
        return "$svet ??????????"
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
                    fragment = CartFragment.newInstance("?????? ??????????:")
                    startFragment(fragment)
                }

                R.id.nav_contact -> {
                    navClickable(3)
                    fragment = ContactFragment.newInstance("????????????????")
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

    fun isNetworkConnect(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}