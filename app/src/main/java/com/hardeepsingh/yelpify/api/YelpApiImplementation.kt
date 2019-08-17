package com.hardeepsingh.yelpify.api

import com.hardeepsingh.yelpify.model.BusinessDetail
import com.hardeepsingh.yelpify.model.BusinessReviews
import com.hardeepsingh.yelpify.model.BusinessesResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class YelpApiImplementation(private val yelpApi: YelpApi, private val scheduler: Scheduler): YelpApiInterface {

    override fun getBusinesses(location: String): Observable<BusinessesResponse> {
        return yelpApi.getBusinesses(location).subscribeOn(scheduler)
    }

    override fun getBusinessDetails(id: String): Observable<BusinessDetail> {
        return yelpApi.getBusinessDetails(id).subscribeOn(scheduler)
    }

    override fun getBusinessReviews(id: String): Observable<BusinessReviews> {
        return yelpApi.getBusinessReviews(id).subscribeOn(scheduler)
    }
}