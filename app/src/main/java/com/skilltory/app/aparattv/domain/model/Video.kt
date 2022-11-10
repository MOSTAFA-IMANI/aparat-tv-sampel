package com.skilltory.app.aparattv.domain.model

data class Video(
    var categoryId: Long = -1,
    val title: String?=null,
    val description: String?=null,
    val sender: List<String>?=null,
    val tags: List<String>?=null,
    val imageUrl: String?=null,
    val likeCount: Double?=null,
    val thumbUrl: String?=null,
    val sendingDate: Int?=null,

)
