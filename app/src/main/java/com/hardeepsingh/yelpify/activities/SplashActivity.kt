package com.hardeepsingh.yelpify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.util.createMainActivityIntent

private const val SPLASH_DELAY = 1000L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({ startActivity(createMainActivityIntent(this)) }, SPLASH_DELAY)
    }
}
