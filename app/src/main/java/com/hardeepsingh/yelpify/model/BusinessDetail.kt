package com.hardeepsingh.yelpify.model

import com.google.gson.annotations.SerializedName

data class BusinessDetail(
    val alias: String,
    val categories: List<Category>,
    val coordinates: Coordinates,
    val display_phone: String,
    val hours: List<Hour>,
    val id: String,
    val image_url: String,
    val is_claimed: Boolean,
    val is_closed: Boolean,
    val location: Location,
    val messaging: Messaging,
    val name: String,
    val phone: String,
    val photos: List<String>,
    val rating: Double,
    val review_count: Int,
    val transactions: List<Any>,
    val url: String
)

data class Hour(
    @SerializedName("open")
    val openHours: List<Open>,
    val hours_type: String,
    val is_open_now: Boolean
)

data class Open(
    val day: Int,
    val end: String,
    val is_overnight: Boolean,
    val start: String
)

data class Messaging(
    val url: String,
    val use_case_text: String
)