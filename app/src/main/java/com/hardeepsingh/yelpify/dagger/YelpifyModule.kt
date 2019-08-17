package com.hardeepsingh.yelpify.dagger

import android.app.Application
import com.hardeepsingh.yelpify.api.AuthInterceptor
import com.hardeepsingh.yelpify.api.YelpApi
import com.hardeepsingh.yelpify.api.YelpApiImplementation
import com.hardeepsingh.yelpify.api.YelpApiInterface
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val YELP_BASE_URL = "https://api.yelp.com/v3/businesses/"

@Module
class YelpifyModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = this.application

    @Provides
    fun provideAuthInterceptor(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(YELP_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideYelpApi(retrofit: Retrofit) = retrofit.create(YelpApi::class.java)

    @Provides
    fun provideYelpApiInterface(yelpApi: YelpApi): YelpApiInterface = YelpApiImplementation(yelpApi, Schedulers.io())
}