package com.micha.evereview.reviews.viewholders

import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Movie
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.reviews.ReviewsViewHolder

class ReviewsViewHolderMovie(
    view: ReviewCardBinding
) : ReviewsViewHolder(view) {
    override fun fill(review: Review<out ReviewItem>) {
        super.fill(review)

        if (review.item !is Movie) {
            return
        }

        val item: Movie = review.item as Movie

        if (item.duration != null) {
            addInfo(
                context.getString(
                    R.string.duration,
                    item.duration!! / 60,
                    item.duration!! % 60
                ),
                R.drawable.duration
            )
        }
        if (item.release != null) {
            addInfo(
                context.getString(R.string.release, item.release!!),
                R.drawable.release
            )
        }
    }
}
