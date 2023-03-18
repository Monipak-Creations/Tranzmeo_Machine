package com.example.machinetest.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.machinetest.data.model.User
import com.example.machinetest.data.model.details_model.UserDetails
import com.example.machinetest.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: UsersRepository,
) : ViewModel() {

    private val _userChannel: Channel<UserDetails> = Channel<UserDetails>()
    val userChannel = _userChannel.receiveAsFlow()

    fun getUserDetails(id: String) = viewModelScope.launch {
        _userChannel.send(repository.getUserDetails(id))

    }


}