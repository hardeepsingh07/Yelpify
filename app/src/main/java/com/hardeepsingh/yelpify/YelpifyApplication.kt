package com.hardeepsingh.yelpify

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.hardeepsingh.yelpify.dagger.DaggerYelpifyComponent
import com.hardeepsingh.yelpify.dagger.YelpifyComponent
import com.hardeepsingh.yelpify.dagger.YelpifyModule

class YelpifyApplication : Application() {

    lateinit var yelpifyComponent: YelpifyComponent

    override fun onCreate() {
        super.onCreate()

        yelpifyComponent = DaggerYelpifyComponent.builder()
            .yelpifyModule(YelpifyModule(this))
            .build()
    }
}

fun provideComponent(activity: AppCompatActivity) = (activity.application as YelpifyApplication).yelpifyComponent