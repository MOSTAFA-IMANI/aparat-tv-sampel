package com.skilltory.app.aparattv.network.model

data class RelationshipsDto(
    val video: Video
){
    data class Video(
        val `data`: List<Data>
    )

    data class Data(
        val id: String,
        val type: String
    )
}