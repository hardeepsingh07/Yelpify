package com.hardeepsingh.yelpify.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hardeepsingh.yelpify.model.Review
import kotlinx.android.synthetic.main.item_reviews.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop


private const val ROUNDED_CORNER = 16

class ReviewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun update(review: Review) {
        view.update(review)
    }

    private fun View.update(review: Review) {
        userImage(review)
        reviewerName.text = review.user.name
        reviewStarRating.setRating(review.rating.toDouble())
        reviewDescription.text = review.text
        reviewDate.text = review.timeCreated
    }

    private fun View.userImage(review: Review) {
        Glide.with(context)
            .load(review.user.imageUrl)
            .apply(RequestOptions()
                .apply { transforms(CenterCrop(), RoundedCorners(ROUNDED_CORNER)) })
            .into(reviewerImage)
    }

}