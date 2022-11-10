package com.skilltory.app.aparattv.repository

import com.skilltory.app.aparattv.network.ApiServiceInterface

class HomeVideoRepository_Impl(
    private val recipeService: ApiServiceInterface,

): HomeVideoRepository {

    override suspend fun getAparatVideoCatogrized() {
        TODO("Not yet implemented")
    }

}