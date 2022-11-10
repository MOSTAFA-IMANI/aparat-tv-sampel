package com.skilltory.app.aparattv.network.response

import com.skilltory.app.aparattv.network.model.IncludedDto
import com.skilltory.app.aparattv.network.model.MetaDataDto

data class AparatHomeVideoResponse(
    val `data`: List<MetaDataDto>,
    val included: List<IncludedDto>,
    val links: LinkDto
){
    data class LinkDto(
        val next: String
    )
}


