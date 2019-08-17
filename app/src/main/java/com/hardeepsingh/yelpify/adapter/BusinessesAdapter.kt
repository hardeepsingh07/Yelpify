package com.hardeepsingh.yelpify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.model.Business
import com.hardeepsingh.yelpify.viewholder.BusinessesViewHolder

class BusinessesAdapter: RecyclerView.Adapter<BusinessesViewHolder>() {

    var businesses = mutableListOf<Business>()

    fun updateList(businesses: List<Business>) {
        this.businesses.clear()
        this.businesses.addAll(businesses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BusinessesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_businesses, parent, false))

    override fun getItemCount() = businesses.size

    override fun onBindViewHolder(holder: BusinessesViewHolder, position: Int) = holder.update(businesses[position])
}