package ru.dudar_ig.swetotehnika.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.ProductViewModel
import ru.dudar_ig.swetotehnika.data.TovarsViewModel
import ru.dudar_ig.swetotehnika.database.Product
import ru.dudar_ig.swetotehnika.database.ProductDbRepo
import ru.dudar_ig.swetotehnika.databinding.FragmentKatBinding
import ru.dudar_ig.swetotehnika.databinding.FragmentProductBinding
import ru.dudar_ig.swetotehnika.ui.MainActivity

private const val ARG_IDD = "param1"
private const val ARG_IDD_NAME = "param2"


class ProductFragment : Fragment(R.layout.fragment_product) {

    private var idName: String? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: FragmentProductBinding?  = null
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
        _binding = FragmentProductBinding.bind(view)

        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = myAdapter

        myAdapter.funCartButtonClick = {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        //            val product = Product()
//            product.id = it.id!!.toInt()
//            product.name = it.name!!
//            product.count = 1
//            product.price = it.price!!
//
//            val productDbRepo = ProductDbRepo.get()
//            productDbRepo.addProduct(product)
           // requireActivity().onBackPressed()
        }


        productViewModel.items?.observe(this, Observer {
            KatId.kat = 4
            it ?: return@Observer
            myAdapter.updateAdapter(it)
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