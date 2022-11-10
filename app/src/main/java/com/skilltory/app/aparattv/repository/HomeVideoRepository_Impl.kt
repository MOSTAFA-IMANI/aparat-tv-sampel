package com.skilltory.app.aparattv.repository

import com.skilltory.app.aparattv.domain.model.HomeUiModel
import com.skilltory.app.aparattv.network.AparatApiService
import com.skilltory.app.aparattv.network.mapper.HomeVideoCategoryMapper

class HomeVideoRepository_Impl(
    private val aparatApi: AparatApiService,
    private val mapper: HomeVideoCategoryMapper
): HomeVideoRepository {

    override suspend fun getAparatVideoCategorized(): HomeUiModel {
       return mapper.mapToDomainModel(aparatApi.getVideos())
    }
}