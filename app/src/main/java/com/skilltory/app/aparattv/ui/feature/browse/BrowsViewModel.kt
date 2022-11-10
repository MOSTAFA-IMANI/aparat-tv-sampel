package com.skilltory.app.aparattv.ui.feature.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skilltory.app.aparattv.domain.model.HomeUiModel
import com.skilltory.app.aparattv.repository.HomeVideoRepository
import com.skilltory.app.aparattv.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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


}