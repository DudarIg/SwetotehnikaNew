package ru.dudar_ig.swetotehnika.data

import com.google.gson.annotations.SerializedName


data class ApiNews(
    @SerializedName("records") val results: List<ResultNews>
)

data class ResultNews(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String,
    @SerializedName("name") val name: String,
    @SerializedName("event") val event: String
)
