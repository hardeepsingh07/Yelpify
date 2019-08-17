package com.hardeepsingh.yelpify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.model.Business
import com.hardeepsingh.yelpify.model.Review
import com.hardeepsingh.yelpify.viewholder.BusinessesViewHolder
import com.hardeepsingh.yelpify.viewholder.ReviewsViewHolder

class ReviewsAdapter: RecyclerView.Adapter<ReviewsViewHolder>() {

    var reviews = mutableListOf<Review>()

    fun updateList(reviews: List<Review>) {
        this.reviews.clear()
        this.reviews.addAll(reviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_reviews, parent, false))

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) = holder.update(reviews[position])
}