package com.example.machinetest.data.model.details_model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class Company(
    @Json(name = "address")
    val address: Address,
    @Json(name = "department")
    val department: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "title")
    val title: String
)