package ru.dudar_ig.swetotehnika.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogApi {
    @GET("/api/category/read.php")
    suspend fun getListCat(): ApiCatalog

    @GET("/api/category/read2.php")
    suspend fun getListCat2(@Query("n") idd:Int): ApiCatalog

    @GET("/api/product/read.php")
    suspend fun getListTovars(@Query("n") idd:Int): ApiCatalog

    @GET("/api/product/read_one.php")
    suspend fun getProduct(@Query("n") idd:Int): ApiCatalog

}

object CatalogApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://swetotehnika.ru")
        .build()

    private val apiService = retrofit.create(CatalogApi::class.java)

    suspend fun getListCat(): List<Tovar> {
        return withContext(Dispatchers.IO) {
            apiService.getListCat()
                .results
                .map { result ->
                    Tovar(
                        result.id,
                        result.name,
                        null,
                        null,
                        null
                    )
                }
        }
    }

    suspend fun getListCat2(id: Int): List<Tovar> {
        return withContext(Dispatchers.IO) {
            apiService.getListCat2(id)
                .results
                .map { result ->
                    Tovar(
                        result.id,
                        result.name,
                        null,
                        null,
                        null
                    )
                }
        }
    }
    suspend fun getListTovars(id: Int): List<Tovar> {
        return withContext(Dispatchers.IO) {
            apiService.getListTovars(id)
                .results
                .map { result ->
                    Tovar(
                        result.id,
                        result.name,
                        null,
                        result.foto,
                        result.price
                    )
                }
        }
    }
    suspend fun getProduct(id: Int): List<Tovar> {
        return withContext(Dispatchers.IO) {
            apiService.getProduct(id)
                .results
                .map { result ->
                    Tovar(
                        result.id,
                        result.name,
                        result.prop,
                        result.foto,
                        result.price
                    )
                }
        }
    }


}