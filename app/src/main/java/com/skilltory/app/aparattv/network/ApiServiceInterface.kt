package com.skilltory.app.aparattv.network

import com.skilltory.app.aparattv.network.response.AparatHomeVideoResponse
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("video/video/list/tagid/1")
    suspend fun getVideos(): AparatHomeVideoResponse
}