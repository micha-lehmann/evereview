package com.micha.evereview.reviews.viewholders

import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Book
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.reviews.ReviewsViewHolder

class ReviewsViewHolderBook(
    view: ReviewCardBinding
) : ReviewsViewHolder(view) {
    override fun fill(review: Review<out ReviewItem>) {
        super.fill(review)

        if (review.item !is Book) {
            return
        }

        if (review.item.author != null) {
            addInfo(
                review.item.author,
                R.drawable.author
            )
        }
        if (review.item.bookGenre != null) {
            addInfo(
                review.item.bookGenre,
                R.drawable.book_genre
            )
        }
    }
}
