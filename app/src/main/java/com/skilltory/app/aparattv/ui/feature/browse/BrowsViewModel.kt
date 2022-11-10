package com.skilltory.app.aparattv.ui.feature.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.domain.model.HomeUiModel
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.repository.HomeVideoRepository
import com.skilltory.app.aparattv.ui.feature.detail.DetailFragmentArgs
import com.skilltory.app.aparattv.utils.Resource
import com.skilltory.app.aparattv.utils.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BrowsViewModel @Inject constructor(
    private val homeVideoRepository: HomeVideoRepository,
) : ViewModel() {
    private val _videoResponse =
        MutableStateFlow<Resource<HomeUiModel>>(Resource.Idle())
    val videoResponse = _videoResponse.asStateFlow()

    private val _navigateToDetail = mutableEventFlow<DetailFragmentArgs>()
    val navigateToDetail = _navigateToDetail.asSharedFlow()

    init {
        loadHomeVideos()
    }

    private fun loadHomeVideos() {
        viewModelScope.launch {
            with(_videoResponse) {
                tryEmit(Resource.Loading())
                tryEmit(Resource.Success(homeVideoRepository.getAparatVideoCategorized()))

                // TODO : Handle network error
            }
        }
    }

    var scrollPos: Pair<Int, Int>? = null
    fun onMovieClicked(movie: Video) {
        if (videoResponse.value is Resource.Success) {
            val categories = (videoResponse.value as Resource.Success<HomeUiModel>).data.categoryList
            val clickedCategory = categories.find { it.id == movie.categoryId }!!

            // Navigating to detail
            _navigateToDetail.tryEmit(
                DetailFragmentArgs( movie)
            )

            // Find category position and movie position
            val catPos = categories.indexOf(clickedCategory)
            val moviePos = clickedCategory.movies.indexOf(movie)

            scrollPos = Pair(catPos, moviePos)

        }
    }

    fun resetScrollPos() {
        scrollPos = null
    }


}