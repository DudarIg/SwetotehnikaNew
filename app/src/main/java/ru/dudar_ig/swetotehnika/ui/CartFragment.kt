package ru.dudar_ig.swetotehnika.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.CartAdapter
import ru.dudar_ig.swetotehnika.database.Product
import ru.dudar_ig.swetotehnika.database.ProductDbRepo
import ru.dudar_ig.swetotehnika.database.ProductsCartViewModel
import ru.dudar_ig.swetotehnika.databinding.FragmentCartBinding

private const val ARG_IDD_NAME = "param2"

class CartFragment : Fragment(R.layout.fragment_cart) {
    private var idName: String? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var zapChip : Chip
    private lateinit var minusChip : Chip
    private lateinit var plusChip : Chip
    private lateinit var countEdit : EditText
    private val product = Product()
    private var count = 1

    private var _binding: FragmentCartBinding? = null
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

        zapChip = view.findViewById(R.id.zap_chip)
        minusChip = view.findViewById(R.id.minus_chip)
        plusChip = view.findViewById(R.id.plus_chip)
        countEdit = view.findViewById(R.id.editTextNumber)


        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        cartAdapter.funItogClick= {
            Toast.makeText(context, "Выполнение отправки данных", Toast.LENGTH_SHORT).show()
        }

        cartAdapter.funDelClick = {
            val productDbRepo = ProductDbRepo.get()
            productDbRepo.delProduct(it)
            //cartAdapter.updateAdapter(it)
        }
        cartAdapter.funEditClick = {
            product.id = it.id!!.toInt()
            product.name = it.name!!
            countEdit.setText(it.count.toString())
            product.price = it.price!!

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        minusChip.setOnClickListener {
            count = countEdit.text.toString().toInt()
            if (count > 1) count--
            countEdit.setText(count.toString())
        }
        plusChip.setOnClickListener {
            count = countEdit.text.toString().toInt()
            count++
            countEdit.setText(count.toString())
        }

        zapChip.setOnClickListener {
            if (countEdit.text.toString().length > 0) {
                val productDbRepo = ProductDbRepo.get()
                product.count = countEdit.text.toString().toInt()
                productDbRepo.updateProduct(product)
                //requireActivity().onBackPressed()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                Toast.makeText(context, "Товар успешно изменен", Toast.LENGTH_SHORT).show()
            }
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