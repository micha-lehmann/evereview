package com.micha.evereview.reviews.viewholders

import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Music
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.reviews.ReviewsViewHolder

class ReviewsViewHolderMusic(
    view: ReviewCardBinding
) : ReviewsViewHolder(view) {
    override fun fill(review: Review<out ReviewItem>) {
        super.fill(review)

        if (review.item !is Music) {
            return
        }

        val item: Music = review.item as Music

        if (item.artist != null) {
            addInfo(
                item.artist!!,
                R.drawable.artist
            )
        }
        if (item.musicGenre != null) {
            addInfo(
                item.musicGenre!!,
                R.drawable.music_genre
            )
        }
    }
}
