package com.micha.evereview.reviews.viewholders

import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.models.Series
import com.micha.evereview.reviews.ReviewsViewHolder

class ReviewsViewHolderSeries(
    view: ReviewCardBinding
) : ReviewsViewHolder(view) {
    override fun fill(review: Review<out ReviewItem>) {
        super.fill(review)

        if (review.item !is Series) {
            return
        }

        val item: Series = review.item as Series

        addInfo(
            context.getString(R.string.season, item.season),
            R.drawable.season
        )
        addInfo(
            context.getString(R.string.episodes, item.episodes),
            R.drawable.episodes
        )
    }
}
