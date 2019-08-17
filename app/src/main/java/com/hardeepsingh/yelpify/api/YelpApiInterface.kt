package com.hardeepsingh.yelpify.api

import com.hardeepsingh.yelpify.model.BusinessDetail
import com.hardeepsingh.yelpify.model.BusinessReviews
import com.hardeepsingh.yelpify.model.BusinessesResponse
import io.reactivex.Observable

interface YelpApiInterface {

    fun getBusinesses(location: String): Observable<BusinessesResponse>

    fun getBusinessDetails(id: String): Observable<BusinessDetail>

    fun getBusinessReviews(id: String): Observable<BusinessReviews>
}