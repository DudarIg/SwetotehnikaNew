package ru.dudar_ig.swetotehnika.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogApi {
    @GET("/api/category/read.php")
    fun getListCat(): Call<ApiCatalog>

    @GET("/api/product/read_home.php")
    fun getListHome(): Call<ApiCatalog>

    @GET("/api/category/read2.php")
    fun getListCat2(@Query("n") idd:Int): Call<ApiCatalog>

    @GET("/api/product/read.php")
    fun getListTovars(@Query("n") idd:Int): Call<ApiCatalog>

    @GET("/api/product/read_one.php")
    fun getProduct(@Query("n") idd:Int): Call<ApiCatalog>

    @GET("/api/product/search.php")
    fun getListSearch(@Query("s") word:String): Call<ApiCatalog>

}

object CatalogApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://swetotehnika.ru")
        .build()

    private val apiService = retrofit.create(CatalogApi::class.java)

    fun loadListCat(): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getListCat().enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }
    fun loadListCat2(id: Int): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getListCat2(id).enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }
    fun loadListTovars(id: Int): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getListTovars(id).enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    tovar.foto = it.foto
                    tovar.price = it.price
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }

    fun loadListSearch(word: String): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getListSearch(word).enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    tovar.foto = it.foto
                    tovar.price = it.price
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }

    fun loadListHome(): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getListHome().enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    tovar.foto = it.foto
                    tovar.price = it.price
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }

    fun loadProduct(id: Int): LiveData<List<Tovar>> {
        val responseLiveData: MutableLiveData<List<Tovar>> = MutableLiveData()
        apiService.getProduct(id).enqueue(object : Callback<ApiCatalog> {
            override fun onResponse(call: Call<ApiCatalog>, response: Response<ApiCatalog>) {
                val jsonCatalog: ApiCatalog? = response.body()
                val catList = mutableListOf<Tovar>()
                jsonCatalog?.results?.forEach {
                    val tovar = Tovar()
                    tovar.id = it.id
                    tovar.name = it.name
                    tovar.prop = it.prop
                    tovar.foto = it.foto
                    tovar.price = it.price
                    catList.add(tovar)
                }
                responseLiveData.postValue(catList)
            }
            override fun onFailure(call: Call<ApiCatalog>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return responseLiveData
    }

}