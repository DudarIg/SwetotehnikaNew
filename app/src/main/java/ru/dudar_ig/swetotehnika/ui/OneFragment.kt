package ru.dudar_ig.swetotehnika.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.HomeAdapter
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.adapter.NewsAdapter
import ru.dudar_ig.swetotehnika.data.HomeViewModel
import ru.dudar_ig.swetotehnika.data.NewsViewModel
import ru.dudar_ig.swetotehnika.data.TitleViewModel
import ru.dudar_ig.swetotehnika.ui.catalog.ProductFragment

class OneFragment : Fragment(R.layout.fragment_one) {

    private val titleViewModel by viewModels<TitleViewModel>()

    private val myAdapter = MyAdapter(ArrayList())
    private val homeViewModel by viewModels<HomeViewModel>()
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as MainActivity).binding.toolbarTitle.text = resources.getString(R.string.name0)
        val recycler1: RecyclerView = view.findViewById(R.id.recycler1)
        recycler1.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler1.adapter = HomeAdapter(titleViewModel.lines1)

        val recycler2: RecyclerView = view.findViewById(R.id.recycler2)
        recycler2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler2.adapter = HomeAdapter(titleViewModel.lines2)


        if ((activity as MainActivity).isNetworkConnect(context)) {

            val newsRrecycler: RecyclerView = view.findViewById(R.id.news_recycler)
            newsRrecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            newsRrecycler.adapter = NewsAdapter(newsViewModel.items)

            KatId.kat = 3
            val recycler3: RecyclerView = view.findViewById(R.id.recycler3)
            recycler3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recycler3.adapter = myAdapter
            homeViewModel.items?.observe(this, Observer {
                myAdapter.updateAdapter(it)
            })

            myAdapter.funProductClick = {
                val fragment = ProductFragment.newInstance(it.id!!.toInt(), it.name!!)
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_container,
                    fragment)?.addToBackStack(null)?.commit()
            }



        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = OneFragment()
    }

}