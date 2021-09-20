package com.micha.evereview.reviews

import com.micha.evereview.models.*
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
                Series("Agents of S.H.I.E.L.D.", 1, 22),
                8.5,
                Date(),
                "Favorite character: Agent Coulson!"
            ),
            Review(
                Music("Tendon", "Igorrr", "Crazy Shit"),
                7.5,
                Date(),
                "Completely crazy but actually pretty good. Not easy to listen to though."
            ),
            Review(
                Book("I don't know any books", "Goethe?", "Drama"),
                0.0,
                Date(),
                "Not a book person."
            )
        ))
    }
}
