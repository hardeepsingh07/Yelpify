package com.hardeepsingh.yelpify.api

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION = "Authorization"
private const val YELP_TOKEN =
    "Bearer r2YmKFOdPQFWItDl5sk470LgeQC_mpXFH76lS11if-3hrL4s-iG1OjZN-_dJvE0k67UPeLL1IMqI7_OSyKKsP-BehBj8ncTgUdJQdz6XDIK0LBi7l1AonFN0o-VUXXYx"

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain
            .proceed(
                chain.request()
                    .newBuilder()
                    .addHeader(AUTHORIZATION, YELP_TOKEN)
                    .build()
            )
}