package com.example.machinetest.api

import com.example.machinetest.data.model.User
import com.example.machinetest.data.model.UsersListResponse
import com.example.machinetest.data.model.details_model.UserDetails
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UsersServiceApi {

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }

    @GET("users")
    suspend fun getUserListResponse(
        @Query("limit") limit: String,
        @Query("skip") skip: String
    ): UsersListResponse

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id : String
    ): UserDetails


}
