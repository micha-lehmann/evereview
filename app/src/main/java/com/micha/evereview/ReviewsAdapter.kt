package com.micha.evereview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.reviewitems.ReviewItem

class ReviewsAdapter(
    private val reviews: List<Review<out ReviewItem>>
) : RecyclerView.Adapter<ReviewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = ReviewCardBinding.inflate(layoutInflater, parent, false)
        return ReviewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.fill(reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

}
