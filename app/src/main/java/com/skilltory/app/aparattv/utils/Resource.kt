package com.skilltory.app.aparattv.utils

import com.skilltory.app.aparattv.network.common.ErrorBody
import com.skilltory.app.aparattv.ui.base.UICommunication


sealed class Resource<T:Any?> {
    class Loading<T>(val isLoading: Boolean) : Resource<T>()
    class Idle<T> : Resource<T>()
    data class Success<T>(
        val data: T,
        val message: String? = null,
    ) : Resource<T>()
    data class Error<T>(
        val message: String? = null,
        val errorBody: ErrorBody? = null,
        val uiCommunication: UICommunication?=null
    ) : Resource<T>()


}
