package ru.dudar_ig.swetotehnika.ui.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.CatViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding


class MainFragment : Fragment(R.layout.fragment_kat) {

    private var _binding: FragmentKatBinding?  = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter(ArrayList())
    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentKatBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = myAdapter

        catViewModel.items?.observe(this, Observer {
            it ?: return@Observer
            myAdapter.updateAdapter(it)
        })


    }

    override fun onResume() {
        KatId.kat = 1
        super.onResume()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}