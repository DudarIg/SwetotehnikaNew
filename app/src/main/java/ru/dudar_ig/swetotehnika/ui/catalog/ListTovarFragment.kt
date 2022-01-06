package ru.dudar_ig.swetotehnika.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.TovarsViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding

private const val ARG_IDD = "param1"
private const val ARG_IDD_NAME = "param2"

class ListTovarFragment : Fragment(R.layout.fragment_kat) {

    private var idName: String? = null

    private var _binding: FragmentKatBinding?  = null
    private val binding get() = _binding!!

    private val myAdapter = MyAdapter(ArrayList())
    private val tovarsViewModel by viewModels<TovarsViewModel>()

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

        myAdapter.funProductClick = {
            val fragment = ProductFragment.newInstance(it.id!!.toInt(), it.name!!)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container,
                fragment)?.addToBackStack(null)?.commit()
        }

        tovarsViewModel.items?.observe(this, Observer {
            KatId.kat = 3
            it ?: return@Observer
            myAdapter.updateAdapter(it)
        })

    }

    override fun onAttach(context: Context) {
        KatId.kat = 3
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            ListTovarFragment().apply {
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