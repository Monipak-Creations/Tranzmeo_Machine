package com.example.machinetest.repository;

import androidx.paging.ExperimentalPagingApi;
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.machinetest.api.UsersServiceApi
import com.example.machinetest.data.model.User
import com.example.machinetest.data.model.details_model.UserDetails
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class UsersRepository @Inject constructor(
    private val usersServiceApi: UsersServiceApi
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getUsersList(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserListPagingSource(
                    usersServiceApi = usersServiceApi
                )
            }
        ).flow
    }

    suspend fun getUserDetails(id: String): UserDetails =
        usersServiceApi.getUserDetails(id)


}