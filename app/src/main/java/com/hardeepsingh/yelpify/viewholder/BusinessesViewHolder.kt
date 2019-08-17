package com.hardeepsingh.yelpify.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hardeepsingh.yelpify.extensions.generateAddress
import com.hardeepsingh.yelpify.extensions.generateCategory
import com.hardeepsingh.yelpify.model.Business
import com.hardeepsingh.yelpify.util.createDetailActivityIntent
import kotlinx.android.synthetic.main.item_businesses.view.*

private const val REVIEWS = "Reviews"
private const val SPACE = " "

class BusinessesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun update(business: Business) {
        view.update(business)
    }

    private fun View.update(business: Business) {
        businessName.text = business.name
        price.text = business.price
        starRating.setRating(business.rating)
        category.text = business.generateCategory()
        reviewsCount.text = "${business.review_count} $REVIEWS"
        address.text = business.generateAddress()
        phoneNumber.text = business.display_phone
        view.setOnClickListener { context.startActivity(createDetailActivityIntent(context, business.id)) }
    }
}