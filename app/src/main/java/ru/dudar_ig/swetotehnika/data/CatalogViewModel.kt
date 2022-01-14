package ru.dudar_ig.swetotehnika.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dudar_ig.swetotehnika.KatId

class CatViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadListCat()
    }
}

class Cat2ViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadListCat2(KatId.id)
    }
}

class TovarsViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadListTovars(KatId.id)
    }
}

class SearchViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadListSearch(KatId.search)
    }
}

class ProductViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadProduct(KatId.id)
    }
}
