package com.hardeepsingh.yelpify.util

import android.content.Context
import android.content.Intent
import com.hardeepsingh.yelpify.activities.DetailsActivity
import com.hardeepsingh.yelpify.activities.MainActivity

const val BUSINESS_ID = "Business-ID"

/**
 * Create an Intent for Main Activity
 */
fun createMainActivityIntent(context: Context) = Intent(context, MainActivity::class.java)


/**
 * Create and Intent for Detail Activity with selected Business ID
 */
fun createDetailActivityIntent(context: Context, id: String) =
    Intent(context, DetailsActivity::class.java)
        .apply {
            putExtra(BUSINESS_ID, id)
        }
