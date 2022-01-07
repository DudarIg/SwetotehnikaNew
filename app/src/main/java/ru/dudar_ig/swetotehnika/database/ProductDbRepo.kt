package ru.dudar_ig.swetotehnika.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val NAME_DATABASE = "films-database"

class ProductDbRepo private constructor(context: Context){

    private val database: ProductDataBase = Room.databaseBuilder(
        context.applicationContext,
        ProductDataBase::class.java,
        NAME_DATABASE
    ).build()

    private val productDao = database.productDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getProducts(): LiveData<List<Product>> = productDao.getProducts()
    fun getProduct(id: Int): LiveData<Product?> = productDao.getProduct(id)

    fun addProduct(product: Product) {
        executor.execute{
            productDao.addProduct(product)
        }
    }

    fun delProduct(product: Product) {
        executor.execute{
            productDao.delProduct(product)
        }
    }

    companion object {
        private var INSTANSE: ProductDbRepo? = null
        fun initialize(context: Context) {
            if (INSTANSE == null) {
                INSTANSE = ProductDbRepo(context)
            }
        }
        fun get(): ProductDbRepo {
            return INSTANSE ?:
            throw IllegalStateException("Корзина не инициализирована")
        }
    }
}