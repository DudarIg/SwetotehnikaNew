package ru.dudar_ig.swetotehnika.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dudar_ig.swetotehnika.KatId

class ProductsCartViewModel: ViewModel() {
    private val productDbRepo: ProductDbRepo
    var listProd: LiveData<List<Product>>
    init {
        productDbRepo = ProductDbRepo.get()
        listProd = productDbRepo.getProducts()
    }
}

class ProductCartViewModel: ViewModel() {
    private val productDbRepo: ProductDbRepo
    var product: LiveData<Product?>
    init {
        productDbRepo = ProductDbRepo.get()
        product = productDbRepo.getProduct(KatId.id)
    }
}