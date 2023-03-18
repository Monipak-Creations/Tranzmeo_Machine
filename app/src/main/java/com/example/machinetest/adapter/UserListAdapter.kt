package com.example.machinetest.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.data.model.User
import com.example.machinetest.databinding.ListItemBinding
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class UserListAdapter @Inject constructor(private val scope: CoroutineScope) :
    PagingDataAdapter<User, UserListAdapter.UserViewHolder>(DiffCallback) {

    lateinit var onClickListener: UserOnClickListener

    inner class UserViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //inner class means we can access parent class' methods and properties like
        init {// getItem

        }


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(user: User) {
            binding.apply {
                firstName.text = "${user.firstName}"
                lastName.text = "${user.lastName}"
                emailId.text = "${user.email}"
                phone.text = "${user.phone}"

                root.setOnClickListener {
                    onClickListener.onUserClick(user.id.toString())
                }

            }
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    fun initOnClickListener(onClickListener: UserOnClickListener) {
        this.onClickListener = onClickListener
    }

    interface UserOnClickListener {
        fun onUserClick(id : String)
    }
}