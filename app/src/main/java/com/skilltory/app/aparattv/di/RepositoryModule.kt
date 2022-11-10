package com.skilltory.app.aparattv.di

import com.skilltory.app.aparattv.network.AparatApiService
import com.skilltory.app.aparattv.network.mapper.HomeVideoCategoryMapper
import com.skilltory.app.aparattv.repository.HomeVideoRepository
import com.skilltory.app.aparattv.repository.HomeVideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeVideoRepository(
        aparatApiService: AparatApiService,
        mapper: HomeVideoCategoryMapper,
    ): HomeVideoRepository {
        return HomeVideoRepositoryImpl(
            aparatApi = aparatApiService,
            mapper = mapper
        )
    }

    @Provides
    fun provideHomeVideoCategoryMapper(): HomeVideoCategoryMapper {
        return HomeVideoCategoryMapper()
    }
}