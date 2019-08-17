package com.hardeepsingh.yelpify.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.adapter.ImagePagerAdapter
import com.hardeepsingh.yelpify.adapter.ReviewsAdapter
import com.hardeepsingh.yelpify.api.YelpApiInterface
import com.hardeepsingh.yelpify.extensions.generateAddress
import com.hardeepsingh.yelpify.extensions.generateCategory
import com.hardeepsingh.yelpify.model.BusinessDetail
import com.hardeepsingh.yelpify.model.BusinessReviews
import com.hardeepsingh.yelpify.provideComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

private const val BUSINESS_ID = "Business-ID"

fun createDetailIntent(context: Context, id: String) =
    Intent(context, DetailsActivity::class.java)
        .apply {
            putExtra(BUSINESS_ID, id)
        }

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var yelpApiInterface: YelpApiInterface

    private val imagePagerAdapter = ImagePagerAdapter()
    private val reviewsAdapter = ReviewsAdapter()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        provideComponent(this).inject(this)
        supportActionBar?.hide()

        imageViewPager.adapter = imagePagerAdapter
        tabLayout.setupWithViewPager(imageViewPager, true)
        reviewsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        reviewsRecyclerView.adapter = reviewsAdapter
    }

    private fun getBusinessId() = intent.getStringExtra(BUSINESS_ID)

    override fun onResume() {
        super.onResume()
        fetchBusinessDetailAndReviews()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun fetchBusinessDetailAndReviews() {
        compositeDisposable.add(
            Observable.zip(
                yelpApiInterface.getBusinessDetails(getBusinessId()),
                yelpApiInterface.getBusinessReviews(getBusinessId()),
                BiFunction { businessDetails: BusinessDetail, businessReviews: BusinessReviews ->
                    Pair(businessDetails, businessReviews)
                }
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    updateBusinessDetailView(it.first)
                    updateReviews(it.second)
                }, {
                    Log.d(DetailsActivity::class.java.simpleName, "$it")
                })
        )
    }

    private fun updateBusinessDetailView(businessDetail: BusinessDetail) {
        imageViewPager.adapter = imagePagerAdapter
        imagePagerAdapter.updateList(businessDetail.photos)
        detailBusinessName.text = businessDetail.name
        detailStarRating.setRating(businessDetail.rating)

        detailAddress.text = businessDetail.generateAddress()
        detailCategory.text = businessDetail.generateCategory()
        detailPhoneNumber.text = businessDetail.display_phone
        hoursLayout.setHours(businessDetail.hours.first().openHours.associateBy { it.day })

        detailWebsite.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(businessDetail.url))) }
    }

    private fun updateReviews(businessReviews: BusinessReviews) {
        reviewsAdapter.updateList(businessReviews.reviews)
    }
}
