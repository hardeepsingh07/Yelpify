package com.hardeepsingh.yelpify.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.adapter.BusinessesAdapter
import com.hardeepsingh.yelpify.api.YelpApiInterface
import com.hardeepsingh.yelpify.provideComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val LOCATION = "Getty Center, in Los Angeles, CA"

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var yelpApiInterface: YelpApiInterface

    private val compositeDisposable = CompositeDisposable()
    private var businessesAdapter = BusinessesAdapter()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = LOCATION
        provideComponent(this).inject(this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView.adapter = businessesAdapter
    }

    override fun onResume() {
        super.onResume()
        fetchBusinesses()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun fetchBusinesses() {
        compositeDisposable.add(
            yelpApiInterface.getBusinesses(LOCATION)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mainProgressBar.visibility = View.VISIBLE
                    mainRecyclerView.visibility = View.GONE
                }
                .doOnComplete {
                    mainProgressBar.visibility = View.GONE
                    mainRecyclerView.visibility = View.VISIBLE
                }
                .subscribe({
                    businessesAdapter.updateList(it.businesses)
                }, {
                    Log.d(MainActivity::class.java.simpleName, "$it")
                })
        )
    }
}
