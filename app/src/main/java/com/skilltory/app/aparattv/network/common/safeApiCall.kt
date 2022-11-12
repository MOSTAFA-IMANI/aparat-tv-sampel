package com.skilltory.app.aparattv.network.common

import android.util.Log
import com.google.gson.Gson
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.utils.Resource
import retrofit2.HttpException
import java.io.IOException

suspend fun <T : Any?> safeApi(
    apiCall: suspend () -> T,
    gson: Gson = Gson(),
): Resource<T> = try {
    Resource.Success(apiCall.invoke())
} catch (throwable: Throwable) {
    Log.e("safeApi", throwable.message.toString())
    when (throwable) {
        is HttpException -> {
            val data = try {
                throwable.response()?.errorBody()?.string()?.let {
                    gson.fromJson(it, ErrorBody::class.java)
                }
            } catch (e: Throwable) {
                null
            }
            data?.status = throwable.code()
            Resource.Error<T>( errorBody = data)
        }
        else -> {
            Resource.Error<T>(message = R.string.network_unknown_error.toString())
        }
    }
}
