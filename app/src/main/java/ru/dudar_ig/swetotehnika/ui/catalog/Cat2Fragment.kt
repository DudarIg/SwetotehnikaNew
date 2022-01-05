package ru.dudar_ig.swetotehnika.ui.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.Cat2ViewModel
import ru.dudar_ig.swetotehnika.data.CatViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding

private const val ARG_IDD = "param1"
private const val ARG_IDD_NAME = "param2"

class Cat2Fragment : Fragment(R.layout.fragment_kat) {
    private var id: String? = null
    private var idName: String? = null

    private var _binding: FragmentKatBinding?  = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter(ArrayList())
    private val cat2ViewModel by viewModels<Cat2ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            KatId.id = it.getInt(ARG_IDD)
            idName = it.getString(ARG_IDD_NAME)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentKatBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = myAdapter

        cat2ViewModel.items?.observe(this, Observer {
            it ?: return@Observer
            myAdapter.updateAdapter(it)
        })


    }
    override fun onResume() {
        KatId.kat = 2
        super.onResume()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            Cat2Fragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IDD, param1)
                    putString(ARG_IDD_NAME, param2)
                }
            }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}