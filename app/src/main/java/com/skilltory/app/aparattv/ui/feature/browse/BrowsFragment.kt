package com.skilltory.app.aparattv.ui.feature.browse

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.ui.MainActivity
import com.skilltory.app.aparattv.ui.base.UICommunicationTasks
import com.skilltory.app.aparattv.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowsFragment : RowsSupportFragment(), UICommunicationTasks {

    private val viewModel: BrowsViewModel by viewModels()
    private val backgroundManager by lazy {
        BackgroundManager.getInstance(requireActivity()).apply {
            attach(requireActivity().window)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        title = getString(R.string.app_name)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        setOnItemViewClickedListener { _, item, _, _ ->
            item as Video
            viewModel.onMovieClicked(item)
        }
        setDynamicBackground()

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

                    handleLoading(
                        requireActivity() as MainActivity,
                        resource.isLoading
                    )
                }
                is Resource.Success -> {
                    displayData(resource.data.categoryList)
                }
                is Resource.Error -> {
                    handleError(
                        requireActivity() as MainActivity,
                        resource)
                }
            }
        }
        viewModel.navigateToDetail.asLiveData().observe(viewLifecycleOwner) {
            findNavController().navigate(
                BrowsFragmentDirections.actionBrowsFragmentToDetailFragment(it.video)
            )
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
        // Scrolling to row/column
        viewModel.scrollPos?.let { (catPos, moviePos) ->
            this.setSelectedPosition(
                catPos,
                true,
                ListRowPresenter.SelectItemViewHolderTask(moviePos)
            )
            viewModel.resetScrollPos()
        }
    }


    private fun setDynamicBackground() {

        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            Log.i("Selecting",
                "setDynamicBackground: ***********${this.selectedPosition}**************")

            if (itemViewHolder?.view != null) {
                val bitmapDrawable =
                    (itemViewHolder.view as ImageCardView).mainImageView.drawable as? BitmapDrawable
                if (bitmapDrawable != null) {
                    backgroundManager.drawable = bitmapDrawable
                }
            }
        }
    }


}