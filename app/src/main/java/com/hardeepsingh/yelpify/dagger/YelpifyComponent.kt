package com.hardeepsingh.yelpify.dagger

import com.hardeepsingh.yelpify.activities.DetailsActivity
import com.hardeepsingh.yelpify.activities.MainActivity
import dagger.Component

@Component(modules = [YelpifyModule::class])
interface YelpifyComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)
}