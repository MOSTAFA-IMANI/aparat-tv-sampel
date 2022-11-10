package com.skilltory.app.aparattv.network.model


data class MetaDataAttributesDto(
    val ads: Boolean,
    val all: Boolean,
    val caption: Any,
    val id: String,
    val line_count: Int,
    val more_type: String,
    val output_type: String,
    val theme: String,
    val title: Title,
    val total: Int
){
    data class Title(
        val badge: String,
        val caption: String,
        val icon: String,
        val priority_type: String,
        val text: String
    )
}