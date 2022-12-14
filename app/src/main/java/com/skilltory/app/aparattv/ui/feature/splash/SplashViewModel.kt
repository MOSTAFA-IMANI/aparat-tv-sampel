package com.skilltory.app.aparattv.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skilltory.app.aparattv.ui.base.BaseViewModel
import com.skilltory.app.aparattv.utils.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _shouldGoToHome = mutableEventFlow<Boolean>()
    val shouldGoToHome = _shouldGoToHome.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1500L)
            _shouldGoToHome.tryEmit(true)
        }
    }
}