package com.example.machinetest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class UsersListResponse(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "users")
    val users: List<User>
)