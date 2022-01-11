package ru.dudar_ig.swetotehnika.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.CartAdapter
import ru.dudar_ig.swetotehnika.database.ProductDbRepo
import ru.dudar_ig.swetotehnika.database.ProductsCartViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentCartBinding

private const val ARG_IDD_NAME = "param2"

class CartFragment : Fragment(R.layout.fragment_cart) {
    private var idName: String? = null

    private var _binding: FragmentCartBinding?  = null
    private val binding get() = _binding!!

    private val cartAdapter = CartAdapter(ArrayList())
    private val productsCartVM by viewModels<ProductsCartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idName = it.getString(ARG_IDD_NAME)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCartBinding.bind(view)

        binding.recyclerCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerCart.adapter = cartAdapter

        cartAdapter.funDelClick = {
            val productDbRepo = ProductDbRepo.get()
            productDbRepo.delProduct(it)
            //cartAdapter.updateAdapter(it)
        }

        productsCartVM.listProd.observe(this, Observer {
            it ?: return@Observer
            cartAdapter.updateAdapter(it)
            if (cartAdapter.itemCount == 0) {
                binding.zeroTv.isVisible = true
                (activity as MainActivity).binding.bottomNav.removeBadge(R.id.nav_zakaz)
            } else {
                binding.zeroTv.isVisible = false
                (activity as MainActivity).binding.bottomNav.getOrCreateBadge(R.id.nav_zakaz)

            }



        })

    }
    override fun onStart() {
        (activity as MainActivity).titleText(idName!!)
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance(param2: String) = CartFragment().apply {
            arguments = Bundle().apply {
                 putString(ARG_IDD_NAME, param2)
            }
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}