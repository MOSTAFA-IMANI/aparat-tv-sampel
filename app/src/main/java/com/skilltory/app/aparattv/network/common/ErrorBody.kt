package com.skilltory.app.aparattv.network.common

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("error") var error: String? = null,
    @SerializedName("errorKey") var errorKey: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
)
