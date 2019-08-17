package com.hardeepsingh.yelpify.api

import com.hardeepsingh.yelpify.model.BusinessDetail
import com.hardeepsingh.yelpify.model.BusinessReviews
import com.hardeepsingh.yelpify.model.BusinessesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpApi {

    @GET("search")
    fun getBusinesses(@Query("location") location: String): Observable<BusinessesResponse>

    @GET("{id}")
    fun getBusinessDetails(@Path("id") id: String): Observable<BusinessDetail>

    @GET("{id}/reviews")
    fun getBusinessReviews(@Path("id") id: String): Observable<BusinessReviews>
}