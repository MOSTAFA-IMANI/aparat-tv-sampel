package com.skilltory.app.aparattv.network.common

import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.ui.base.UICommunication
import com.skilltory.app.aparattv.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class ApiResponseHandler<NetworkObject : Any?> constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiCall: suspend () -> NetworkObject,
    private val viewModelScope: CoroutineScope,
) {


    suspend fun getSyncResult() {
        process()
    }

    fun getResult() {
        viewModelScope.launch(dispatcher) {
            process()
        }
    }

    private suspend fun process() {
        handleLoading(true)
        when (val result = safeApi(apiCall = apiCall)) {
            is Resource.Success<*> ->
                handleSuccessResult(result as Resource.Success<NetworkObject>)
            is Resource.Error -> {
                val uiCommunication = UICommunication.ShowSnackbar(
                    message = R.string.network_unknown_error.toString(),
                    actionTitle = R.string.retry.toString(),
                    action = ::getResult,
                )

                handleError(result.errorBody, uiCommunication)
            }
            else -> {
                // no need to do something else
            }
        }
        handleLoading(false)
    }

    abstract fun handleLoading(enable: Boolean)
    abstract fun handleSuccessResult(successResult: Resource.Success<NetworkObject>)
    abstract fun handleError(errorBody: ErrorBody? = null, uiCommunication: UICommunication)
}