package com.example.machinetest.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.machinetest.api.UsersServiceApi
import com.example.machinetest.data.model.User
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 0
const val NETWORK_PAGE_SIZE = 10
const val CO_EFFICIENT = 3

class UserListPagingSource @Inject constructor(
    private val usersServiceApi: UsersServiceApi
) :
    PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
                val response =
                    usersServiceApi.getUserListResponse(limit = "10", skip = position.toString())
                val repos = response.users
                val nextKey = if (repos.isEmpty()) {
                    null
                } else {
                    if(
                        params.loadSize == NETWORK_PAGE_SIZE
                    )   {
                        position + params.loadSize

                    }
                    else {
//                        position + (params.loadSize / NETWORK_PAGE_SIZE)
                        position + (params.loadSize/CO_EFFICIENT)
                    }
                }
                LoadResult.Page(
                    data = repos,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }


    }
}