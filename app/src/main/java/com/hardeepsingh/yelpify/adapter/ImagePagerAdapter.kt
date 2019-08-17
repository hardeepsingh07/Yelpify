package com.hardeepsingh.yelpify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.hardeepsingh.yelpify.R


class ImagePagerAdapter: PagerAdapter() {

    private val urls = mutableListOf<String>()

    fun updateList(urls: List<String>) {
        this.urls.clear()
        this.urls.addAll(urls)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.pager_image_view, null) as ImageView
        Glide.with(container.context).load(urls[position]).centerCrop().into(view)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) = container.removeView(view as View)

    override fun isViewFromObject(view: View, pageObject: Any) = view == pageObject

    override fun getCount() = urls.size
}