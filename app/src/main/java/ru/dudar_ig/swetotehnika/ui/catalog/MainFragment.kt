package ru.dudar_ig.swetotehnika.ui.catalog

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.CatViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding
import ru.dudar_ig.swetotehnika.ui.MainActivity


class MainFragment : Fragment(R.layout.fragment_kat) {

    private var _binding: FragmentKatBinding?  = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter()
    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentKatBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = myAdapter

        myAdapter.funCatClick = {
                val fragment = Cat2Fragment.newInstance(it.id!!.toInt(), it.name!!)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                    fragment)?.addToBackStack(null)?.commit()
        }

        catViewModel.items?.observe(viewLifecycleOwner, Observer {
            KatId.kat = 1
            it ?: return@Observer
            myAdapter.updateAdapter(it)
            binding.pBar.isVisible = false
        })
    }
    override fun onStart() {
        (activity as MainActivity).titleText(resources.getString(R.string.cat_name))
        super.onStart()
    }
    override fun onAttach(context: Context) {
        KatId.kat = 1
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onStop() {
        (activity as MainActivity).navLists[1].isClickable = true
        super.onStop()
    }
}