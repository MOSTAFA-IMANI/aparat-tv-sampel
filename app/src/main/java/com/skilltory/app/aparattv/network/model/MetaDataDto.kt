package com.skilltory.app.aparattv.network.model

data class MetaDataDto(
    val attributes: MetaDataAttributesDto,
    val id: String,
    val relationships: RelationshipsDto,
    val type: String
){

}