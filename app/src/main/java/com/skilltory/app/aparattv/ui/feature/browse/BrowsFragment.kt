package com.skilltory.app.aparattv.ui.feature.browse

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.asLiveData
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.utils.Constants
import com.skilltory.app.aparattv.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowsFragment:BrowseSupportFragment() {

    private val viewModel: BrowsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.app_name)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

    }


    private fun observeData() {
        viewModel.videoResponse.asLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Idle -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.i(Constants.TAG_HOME, "observeData: ${resource.data.categoryList}")
                    displayData(resource.data.categoryList)
//                    startEntranceTransition()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun displayData(categories: List<Category>) {

    }



}