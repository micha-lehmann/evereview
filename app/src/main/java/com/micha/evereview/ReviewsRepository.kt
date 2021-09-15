package com.micha.evereview

import com.micha.evereview.models.Movie
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.models.Series
import java.util.*

class ReviewsRepository {
    fun getReviews(onSuccess: (reviews: List<Review<out ReviewItem>>) -> Unit) {
        // TODO: Implement.

        onSuccess(listOf(
            Review(
                Movie("Avengers", 143, 2012),
                7.5,
                Date(),
                "Good film."
            ),
            Review(
                Movie("The Incredible Hulk", 112, 2008),
                3.0,
                Date(),
                null
            ),
            Review(
                Series("Agents of SHIELD", 1, 22),
                8.5,
                Date(),
                "Favorite character: Agent Coulson!"
            )
        ))
    }
}
