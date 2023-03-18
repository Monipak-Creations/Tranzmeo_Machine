package com.example.machinetest.data.model.details_model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "address")
    val address: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "coordinates")
    val coordinates: Coordinates,
    @Json(name = "postalCode")
    val postalCode: String,
    @Json(name = "state")
    val state: String
)