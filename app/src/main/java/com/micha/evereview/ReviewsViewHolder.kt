package com.micha.evereview

import android.annotation.SuppressLint
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.reviewitems.Movie
import com.micha.evereview.reviewitems.ReviewItem
import java.text.SimpleDateFormat

class ReviewsViewHolder(
    private val view: ReviewCardBinding
) : RecyclerView.ViewHolder(view.root) {
    @SuppressLint("SimpleDateFormat")
    fun fill(review: Review<out ReviewItem>) {
        val context = view.root.context
        view.title.text = review.item.title
        view.ratingText.text = context.getString(R.string.rating, review.rating, 10) // TODO: Magic Number

        view.duration.visibility = GONE
        view.release.visibility = GONE

        if (review.item is Movie) {
            view.duration.visibility = VISIBLE
            view.durationText.text = "${review.item.duration / 60}:${review.item.duration % 60}"
            view.release.visibility = VISIBLE
            view.releaseText.text = "${review.item.release}"
        }
    }
}
