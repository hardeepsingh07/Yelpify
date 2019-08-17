package com.hardeepsingh.yelpify.customlayouts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.hardeepsingh.yelpify.R

private const val ZERO = 0
private const val HALF = 0.5

/**
 * Custom layout to handle ratings logic
 */
class StarRatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        orientation = HORIZONTAL
    }

    fun setRating(inputRating: Double) {
        removeAllViews()
        var rating = inputRating
        while (rating > ZERO) {
            addView(generateStar(if (rating.equals(HALF)) R.drawable.start_half_filled else R.drawable.start_filled))
            rating--
        }
    }

    private fun generateStar(id: Int) =
        (LayoutInflater
            .from(context)
            .inflate(R.layout.star_rating_layout, this, false) as ImageView)
            .apply {
                setImageDrawable(context.getDrawable(id))
            }
}