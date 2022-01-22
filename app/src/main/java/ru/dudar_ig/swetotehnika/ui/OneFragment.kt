package ru.dudar_ig.swetotehnika.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.HomeAdapter
import ru.dudar_ig.swetotehnika.data.Cat2ViewModel
import ru.dudar_ig.swetotehnika.data.TitleViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentOneBinding


class OneFragment : Fragment(R.layout.fragment_one) {

//    private var _binding: FragmentOneBinding? = null
//    private val binding get() = _binding!!

    //private val homeAdapter = HomeAdapter(List())
    private val titleViewModel by viewModels<TitleViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).binding.toolbarTitle.text = resources.getString(R.string.app_name)
        val recycler1: RecyclerView = view.findViewById(R.id.recycler1)
        recycler1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler1.adapter = HomeAdapter(titleViewModel.lines1)

        val recycler2: RecyclerView = view.findViewById(R.id.recycler2)
        recycler2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler2.adapter = HomeAdapter(titleViewModel.lines2)

    }
    companion object {
        @JvmStatic
        fun newInstance() = OneFragment()
    }

}