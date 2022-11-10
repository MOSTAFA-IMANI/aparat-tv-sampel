package com.skilltory.app.aparattv.network.model

data class AparatVideoDto(
    val `data`: List<Data>,
    val included: List<Included>,
    val links: Links
){

    data class Data(
        val attributes: Attributes,
        val id: String,
        val relationships: Relationships,
        val type: String
    )

    data class Included(
        val attributes: AttributesX,
        val id: String,
        val relationships: RelationshipsX,
        val type: String
    )

    data class Links(
        val next: String
    )

    data class Attributes(
        val ads: Boolean,
        val all: Boolean,
        val button: Button,
        val caption: Any,
        val id: String,
        val line_count: Int,
        val link: LinkX,
        val more_type: String,
        val output_type: String,
        val theme: String,
        val title: Title,
        val total: Int
    )

    data class Relationships(
        val channel: Channel,
        val video: Video
    )

    data class Button(
        val link: Link,
        val text: String
    )

    data class LinkX(
        val showAll: String,
        val text: String
    )

    data class Title(
        val badge: String,
        val caption: Any,
        val icon: String,
        val priority_type: String,
        val text: String
    )

    data class Link(
        val link_key: String,
        val link_type: String
    )

    data class Channel(
        val `data`: DataX
    )

    data class Video(
        val `data`: List<DataX>
    )

    data class DataX(
        val id: String,
        val type: String
    )

    data class AttributesX(
        val `360d`: String,
        val autoplay: Boolean,
        val avatar: String,
        val big_poster: String,
        val brand_priority: String,
        val caption: String,
        val catId: String,
        val content_type: String,
        val date_exact: String,
        val description: String,
        val displayName: String,
        val duration: String,
        val file_link: Any,
        val file_link_all: Any,
        val follower_cnt: String,
        val frame: String,
        val hd: String,
        val id: String,
        val income_type: String,
        val isAbroad: Boolean,
        val isCompany: Boolean,
        val isHidden: Boolean,
        val like: Like,
        val like_cnt: String,
        val link: String,
        val link_toggle_push_follow: Any,
        val message_cnt: Int,
        val meta: Meta,
        val name: String,
        val official: String,
        val pic: String,
        val preview_src: String,
        val priority: String,
        val priority_type: String,
        val process: String,
        val profilePhoto: String,
        val push_follow_status: Any,
        val sdate: String,
        val sdate_rss: String,
        val sdate_timediff: Int,
        val sender_name: String,
        val sensitive: Boolean,
        val share: Any,
        val small_poster: String,
        val status: String,
        val tag_rel_id: String,
        val tags: List<String>,
        val title: String,
        val uid: String,
        val userid: Int,
        val username: String,
        val videovisit: Any,
        val visit_cnt: String,
        val visit_cnt_int: String,
        val watch: Watch
    )

    data class RelationshipsX(
        val channel: Channel,
    )

    data class Like(
        val cnt: String
    )

    data class Meta(
        val _boosted: Boolean,
        val _dateScore: Double,
        val _explainPersonalization: List<ExplainPersonalization>,
        val _id: String,
        val _index: String,
        val _indexScore: Int,
        val _personalized: Boolean,
        val _recommendationType: String,
        val _score: Double,
        val _type: String
    )

    data class Watch(
        val avgWatchDuration: Int,
        val avgWatchDurationLabel: String,
        val durationPercentWatch: Int,
        val monthWatch: String,
        val text: String,
        val watchTimeMinStr: String
    )

    data class ExplainPersonalization(
        val channel: String,
        val onTheRise: Boolean,
        val picked: Boolean,
        val recommendationType: String,
        val rowType: String,
        val score: Double,
        val scoreAfter: Double,
        val type: String
    )


}


