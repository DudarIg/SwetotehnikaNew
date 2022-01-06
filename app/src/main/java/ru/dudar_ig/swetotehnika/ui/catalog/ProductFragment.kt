package ru.dudar_ig.swetotehnika.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.ProductViewModel
import ru.dudar_ig.swetotehnika.data.TovarsViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding
import ru.dudar_ig.swetotehnika.ui.MainActivity

private const val ARG_IDD = "param1"
private const val ARG_IDD_NAME = "param2"

class ProductFragment : Fragment(R.layout.fragment_kat) {

    private var idName: String? = null

    private var _binding: FragmentKatBinding?  = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter(ArrayList())
    private val productViewModel by viewModels<ProductViewModel>()

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

        productViewModel.items?.observe(this, Observer {
            KatId.kat = 4
            it ?: return@Observer
            myAdapter.updateAdapter(it)
            binding.pBar.isVisible = false
        })


    }

    override fun onStart() {
        (activity as MainActivity).titleText(idName!!)
        super.onStart()
    }

    override fun onAttach(context: Context) {
        KatId.kat = 4
        super.onAttach(context)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            ProductFragment().apply {
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