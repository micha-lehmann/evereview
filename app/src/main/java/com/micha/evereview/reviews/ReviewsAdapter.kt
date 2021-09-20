package com.micha.evereview.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.*
import com.micha.evereview.reviews.viewholders.ReviewsViewHolderBook
import com.micha.evereview.reviews.viewholders.ReviewsViewHolderMovie
import com.micha.evereview.reviews.viewholders.ReviewsViewHolderMusic
import com.micha.evereview.reviews.viewholders.ReviewsViewHolderSeries

enum class ReviewItemType {
    UNKNOWN,
    MOVIE,
    SERIES,
    MUSIC,
    BOOK
}

class ReviewsAdapter(
    private val reviews: List<Review<out ReviewItem>>
) : RecyclerView.Adapter<ReviewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = ReviewCardBinding.inflate(layoutInflater, parent, false)

        return when (viewType) {
            ReviewItemType.MOVIE.ordinal -> ReviewsViewHolderMovie(layout)
            ReviewItemType.SERIES.ordinal -> ReviewsViewHolderSeries(layout)
            ReviewItemType.MUSIC.ordinal -> ReviewsViewHolderMusic(layout)
            ReviewItemType.BOOK.ordinal -> ReviewsViewHolderBook(layout)
            else -> ReviewsViewHolder(layout)
        }
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.clear()
        holder.fill(reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (reviews[position].item) {
            is Movie -> ReviewItemType.MOVIE.ordinal
            is Series -> ReviewItemType.SERIES.ordinal
            is Music -> ReviewItemType.MUSIC.ordinal
            is Book -> ReviewItemType.BOOK.ordinal
            else -> ReviewItemType.UNKNOWN.ordinal
        }
    }
}
