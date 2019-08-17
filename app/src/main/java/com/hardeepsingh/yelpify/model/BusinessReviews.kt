package com.hardeepsingh.yelpify.model

import com.google.gson.annotations.SerializedName

data class BusinessReviews(
    @SerializedName("possible_languages")
    val possibleLanguages: List<String>,
    val reviews: List<Review>,
    val total: Int
)

data class Review(
    val id: String,
    val rating: Int,
    val text: String,
    @SerializedName("time_created")
    val timeCreated: String,
    val url: String,
    val user: User
)

data class User(
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String
)