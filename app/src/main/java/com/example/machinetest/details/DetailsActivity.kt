package com.example.machinetest.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.machinetest.databinding.ActivityDetailsBinding
import com.example.machinetest.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding = ActivityDetailsBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString(Constants.USER_ID)
            if (value != null) {
                viewModel.getUserDetails(value)
            }
        }

        this.lifecycleScope.launchWhenStarted {
            viewModel.userChannel.collectLatest {
                binding.apply {
                it.let {
                    firstName.text = it.firstName
                    lastName.text = it.lastName
                    emailId.text = it.email
                    phone.text = it.phone
                    domain.text = it.domain
                    bloodGroup.text = it.bloodGroup
                    height.text = "${it.height} cm"
                    weight.text = "${it.weight} kg"

                    imageView.load(it.image){
                        transformations(CircleCropTransformation())
                    }
                }

                }
            }
        }

    }
}