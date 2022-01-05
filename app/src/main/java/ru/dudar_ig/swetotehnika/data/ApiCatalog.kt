package ru.dudar_ig.swetotehnika.data

import com.google.gson.annotations.SerializedName


data class ApiCatalog(
    @SerializedName("records") val results: List<Result>
)

data class Result(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("prop") val prop: String?,
    @SerializedName("foto") val foto: String?,
    @SerializedName("price") val price: String?
)
