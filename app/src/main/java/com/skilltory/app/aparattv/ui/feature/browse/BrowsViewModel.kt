package com.skilltory.app.aparattv.ui.feature.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skilltory.app.aparattv.domain.model.HomeUiModel
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.network.common.ApiResponseHandler
import com.skilltory.app.aparattv.network.common.ErrorBody
import com.skilltory.app.aparattv.repository.HomeVideoRepository
import com.skilltory.app.aparattv.ui.base.BaseViewModel
import com.skilltory.app.aparattv.ui.base.UICommunication
import com.skilltory.app.aparattv.ui.feature.detail.DetailFragmentArgs
import com.skilltory.app.aparattv.utils.Resource
import com.skilltory.app.aparattv.utils.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class BrowsViewModel @Inject constructor(
    private val homeVideoRepository: HomeVideoRepository,
) : BaseViewModel() {
    private val _videoResponse =
        MutableStateFlow<Resource<HomeUiModel>>(Resource.Idle())
    val videoResponse = _videoResponse.asStateFlow()

    private val _navigateToDetail = mutableEventFlow<DetailFragmentArgs>()
    val navigateToDetail = _navigateToDetail.asSharedFlow()

    init {
        loadHomeVideos()
    }
    private lateinit var successResult: Resource.Success<HomeUiModel>

    private fun loadHomeVideos() {
        viewModelScope.launch {
            with(_videoResponse) {

                object : ApiResponseHandler<HomeUiModel>(
                    dispatcher = Dispatchers.Main,
                    apiCall = { homeVideoRepository.getAparatVideoCategorized() },
                    viewModelScope = viewModelScope,

                    ) {
                    override fun handleLoading(enable: Boolean) {
                        tryEmit(Resource.Loading(enable))
                    }

                    override fun handleSuccessResult(successResult: Resource.Success<HomeUiModel>) {
                        this@BrowsViewModel.successResult = successResult
                        tryEmit(successResult)
                    }

                    override fun handleError(
                        errorBody: ErrorBody?,
                        uiCommunication: UICommunication,
                    ) {
                        tryEmit(Resource.Error(errorBody = errorBody,
                            uiCommunication = uiCommunication))
                    }
                }.getResult()
            }

        }
    }

    var scrollPos: Pair<Int, Int>? = null
    fun onMovieClicked(movie: Video) {

        if(this::successResult.isInitialized ){
            val categories = successResult.data.categoryList
            val clickedCategory = categories.find { it.id == movie.categoryId }!!

            // Navigating to detail
            _navigateToDetail.tryEmit(
                DetailFragmentArgs(movie)
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