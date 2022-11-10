package com.skilltory.app.aparattv.domain.model

data class Category(
    val id: Long,
    val genre: String,
    val movies: List<Video>
)