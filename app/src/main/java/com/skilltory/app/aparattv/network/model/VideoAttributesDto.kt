package com.skilltory.app.aparattv.network.model


data class VideoAttributesDto(
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
    val like:Like,
    val like_cnt: String,
    val link: String,
    val link_toggle_push_follow: Any,
    val message_cnt: Int,
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
){
    data class Like(
        val cnt: String
    )

    data class Watch(
        val avgWatchDuration: Int,
        val avgWatchDurationLabel: String,
        val durationPercentWatch: Int,
        val monthWatch: String,
        val text: String,
        val watchTimeMinStr: String
    )
}
