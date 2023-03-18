package com.example.machinetest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class Hair(
    @Json(name = "color")
    val color: String,
    @Json(name = "type")
    val type: String
)