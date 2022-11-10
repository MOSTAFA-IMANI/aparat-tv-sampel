package com.skilltory.app.aparattv.network

import com.skilltory.app.aparattv.network.model.AparatVideoDto
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("video/video/list/tagid/1")
    suspend fun getVideos(): AparatVideoDto
}