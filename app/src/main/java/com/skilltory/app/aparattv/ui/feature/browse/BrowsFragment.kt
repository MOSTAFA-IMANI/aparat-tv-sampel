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
import androidx.navigation.fragment.findNavController
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.utils.Constants
import com.skilltory.app.aparattv.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowsFragment:BrowseSupportFragment() {

    private val viewModel: BrowsViewModel by viewModels()
    private val backgroundManager by lazy {
        BackgroundManager.getInstance(requireActivity()).apply {
            attach(requireActivity().window)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        title = getString(R.string.app_name)

        if (savedInstanceState == null) {
//            prepareEntranceTransition()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

    }




    override fun getSelectedPosition(): Int {
        Log.i("Selecting", "getSelectedPosition: ${super.getSelectedPosition()}")
        return super.getSelectedPosition().also {
            Log.i("Selecting", "getSelectedPosition: $it")

        }
    }

    private fun observeData() {
        viewModel.videoResponse.asLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Idle -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
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
        val adapter = ArrayObjectAdapter(ListRowPresenter())
        for (category in categories) {
            val headerItem = HeaderItem(category.id, category.genre)
            val rowAdapter = ArrayObjectAdapter(VideoCardPresenter())
            for (movie in category.movies) {
                rowAdapter.add(movie)
            }
            adapter.add(ListRow(headerItem, rowAdapter))
        }


        this.adapter = adapter

    }
}