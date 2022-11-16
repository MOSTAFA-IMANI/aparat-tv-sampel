package com.skilltory.app.aparattv.ui.feature.browse

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.asLiveData
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.ui.MainActivity
import com.skilltory.app.aparattv.ui.base.UICommunicationTasks
import com.skilltory.app.aparattv.utils.Resource
import com.skilltory.app.aparattv.utils.extension.navigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BrowsFragment : BrowseSupportFragment(), UICommunicationTasks {
    companion object {

        private const val BACKGROUND_RESOURCE_ID = R.drawable.image_placeholder
    }
    private val viewModel: BrowsViewModel by viewModels()
    private val backgroundManager by lazy {
        BackgroundManager.getInstance(requireActivity()).apply {
            attach(requireActivity().window)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * we can use [setBadgeDrawable]
     * to set an icon without customize whole layout
     * but it just add a single  image view
     * by customize whole layout of title header  we have more options
     * to add new views or modify the existing views like [setTitle]
     * */

    override fun onInflateTitleView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater!!.inflate( R.layout.browse_title_custom_header_layout, parent, false).also {
            val textView = it.findViewById<TextView>(androidx.leanback.R.id.title_text)
            val newParams = FrameLayout.LayoutParams(
                FrameLayout  .LayoutParams.WRAP_CONTENT,
                FrameLayout .LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
            textView.apply {
                layoutParams = newParams
                background = null
            }
            it.background = ContextCompat.getDrawable(requireContext(),R.drawable.title_background)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headersState = HEADERS_DISABLED;
        title = getString(R.string.brows_title)
    }


    override fun onResume() {
        super.onResume()
        setAppBarTitleText(requireActivity() as MainActivity,
            getString(R.string.brows_title))
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
            goToDetail(it.video)
        }
    }
    private fun goToDetail(video: Video) {
        val action = BrowsFragmentDirections.actionBrowsFragmentToDetailFragment(video)
        navigate(action)
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

    private fun manageAppBarVisibility(selectedPosition: Int) {
        if(selectedPosition==0){
            expandAppBar(requireActivity() as MainActivity)
        }else{
            collapseAppBar(requireActivity() as MainActivity)
        }
    }
    private fun setDynamicBackground() {

        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            Log.i("Selecting",
                "setDynamicBackground: ***********${this.selectedPosition}**************")
            manageAppBarVisibility(this.selectedPosition)
            //for faster move I disabled dynamic image changing
            //and use default image for background
            //
            // findAndSetMainBackground(itemViewHolder)
            setDefaultMainBackground()

        }
    }

    /**
     * Set The default Background for main page
     * */
    private fun setDefaultMainBackground() {
        backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),BACKGROUND_RESOURCE_ID)

    }
    /**
     * Set The image Background for main page
     * */
    private fun findAndSetMainBackground(itemViewHolder: Presenter.ViewHolder) {
        if (itemViewHolder?.view != null) {
            val bitmapDrawable =
                (itemViewHolder.view as ImageCardView).mainImageView.drawable as? BitmapDrawable
            if (bitmapDrawable != null) {
                backgroundManager.drawable =bitmapDrawable
            }
            else{
                setDefaultMainBackground()
            }
        }

    }


}