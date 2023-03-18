package com.example.machinetest.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.R
import com.example.machinetest.adapter.UserListAdapter
import com.example.machinetest.data.model.User
import com.example.machinetest.databinding.ActivityMainBinding
import com.example.machinetest.details.DetailsActivity
import com.example.machinetest.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserListAdapter.UserOnClickListener {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        val adapter = UserListAdapter(this.lifecycleScope)
        adapter.initOnClickListener(this@MainActivity)

        binding.apply {
            userListView.setHasFixedSize(true)
            userListView.adapter = adapter
        }

        this.lifecycleScope.launch {
            viewModel.usersList.collectLatest { value: PagingData<User> ->
                adapter.submitData(value)
            }
        }


    }

    override fun onUserClick(id: String) {
        startActivity(
            Intent(
                this@MainActivity,
                DetailsActivity::class.java
            ).putExtra(Constants.USER_ID, id)
        )
    }


}