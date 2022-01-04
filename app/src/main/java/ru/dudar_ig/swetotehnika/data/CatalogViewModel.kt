package ru.dudar_ig.swetotehnika.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatViewModel: ViewModel() {
    val items : LiveData<List<Tovar>>?
    init {
        items = CatalogApiImpl.loadListCat()
    }
}

//class Cat2ViewModel : ViewModel() {
//    private val _items = MutableLiveData<List<Tovar>>()
//    val items: LiveData<List<Tovar>> get() = _items
//    init {
//        viewModelScope.launch {
//            _items.value = CatalogApiImpl.getListCat2()
//        }
//    }
//}
//
//class TovarListViewModel : ViewModel() {
//    private val _items = MutableLiveData<List<Tovar>>()
//    val items: LiveData<List<Tovar>> get() = _items
//    init {
//        viewModelScope.launch {
//            _items.value = CatalogApiImpl.getListTovars()
//        }
//    }
//}
//class TovarViewModel : ViewModel() {
//    private val _items = MutableLiveData<List<Tovar>>()
//    val items: LiveData<List<Tovar>> get() = _items
//    init {
//        viewModelScope.launch {
//            _items.value = CatalogApiImpl.getProduct()
//        }
//    }
//}