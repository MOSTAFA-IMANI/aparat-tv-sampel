package com.skilltory.app.aparattv.network.model

data class IncludedDto(
    val attributes: VideoAttributesDto,
    val id: String,
    val relationships: RelationshipsDto,
    val type: String
)