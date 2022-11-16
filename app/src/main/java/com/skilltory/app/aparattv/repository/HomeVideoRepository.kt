package com.skilltory.app.aparattv.repository

import com.skilltory.app.aparattv.domain.model.HomeUiModel

interface HomeVideoRepository {

    suspend fun getAparatVideoCategorized(): HomeUiModel

    suspend fun getDara()
}