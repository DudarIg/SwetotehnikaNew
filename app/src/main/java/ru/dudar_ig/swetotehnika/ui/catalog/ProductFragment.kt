package ru.dudar_ig.swetotehnika.ui.catalog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.adapter.MyAdapter
import ru.dudar_ig.swetotehnika.data.ProductViewModel
import ru.dudar_ig.swetotehnika.database.Product
import ru.dudar_ig.swetotehnika.database.ProductDbRepo
import ru.dudar_ig.swetotehnika.databinding.FragmentProductBinding
import ru.dudar_ig.swetotehnika.ui.MainActivity


private const val ARG_IDD = "param1"
private const val ARG_IDD_NAME = "param2"

class ProductFragment : Fragment(R.layout.fragment_product) {

    private var idName: String? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var zapChip : Chip
    private lateinit var minusChip : Chip
    private lateinit var plusChip : Chip
    private lateinit var countEdit : EditText
    val product = Product()
    var count : Int = 1

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

        zapChip = view.findViewById(R.id.zap_chip)
        minusChip = view.findViewById(R.id.minus_chip)
        plusChip = view.findViewById(R.id.plus_chip)
        countEdit = view.findViewById(R.id.editTextNumber)

        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = myAdapter

        myAdapter.funCartButtonClick = {
            product.id = it.id!!.toInt()
            product.name = it.name!!
            product.count = count
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
                productDbRepo.addProduct(product)
                (activity as MainActivity).binding.bottomNav.getOrCreateBadge(R.id.nav_zakaz)
                Toast.makeText(context, "Товар успешно добавлен в заказ", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
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