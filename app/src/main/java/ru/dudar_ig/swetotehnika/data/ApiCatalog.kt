package ru.dudar_ig.swetotehnika.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiCatalog(
    @Json(name = "records") val results: List<Result>
)

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "prop") val prop: String?,
    @Json(name = "foto") val foto: String?,
    @Json(name = "price") val price: String?
)
