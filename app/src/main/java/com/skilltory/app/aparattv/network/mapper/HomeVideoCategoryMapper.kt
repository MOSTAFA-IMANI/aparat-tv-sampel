package com.skilltory.app.aparattv.network.mapper

import com.skilltory.app.aparattv.domain.model.Category
import com.skilltory.app.aparattv.domain.model.HomeUiModel
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.domain.util.DomainMapper
import com.skilltory.app.aparattv.network.response.AparatHomeVideoResponse
import com.skilltory.app.aparattv.utils.extension.toUniqueId

class HomeVideoCategoryMapper : DomainMapper<AparatHomeVideoResponse, HomeUiModel> {
    override fun mapToDomainModel(model: AparatHomeVideoResponse): HomeUiModel {
        val resultList = ArrayList<Category>()
        val metaDataOfCategoryList = model.data
        val allVideoList = model.included
        metaDataOfCategoryList.forEach { aparatCategory ->
            val videoListOfThisCategory = ArrayList<Video>()
            aparatCategory.relationships.video.data.forEach { vidoData ->
                val currentVideo = allVideoList.find {
                    it.id == vidoData.id
                }
                videoListOfThisCategory.add(Video(
                    description = currentVideo?.attributes?.description,
                    tags = currentVideo?.attributes?.tags,
                    imageUrl = currentVideo?.attributes?.big_poster,
                    title = currentVideo?.attributes?.title,
                    likeCount = currentVideo?.attributes?.like_cnt?.toDouble(),
                    thumbUrl = currentVideo?.attributes?.small_poster,
                    categoryId = aparatCategory.id.toUniqueId()
                ))
            }
            val currentCategory = Category(aparatCategory.id.toUniqueId(),
                aparatCategory.attributes.title.text,
                videoListOfThisCategory)

            resultList.add(currentCategory)
        }

        return HomeUiModel(resultList)
    }


    override fun mapFromDomainModel(domainModel: HomeUiModel): AparatHomeVideoResponse? {

        //no need to implement this method
        // for this project
        // We don't Send Data To Server Yet
        // maybe Later
        return null
    }
}