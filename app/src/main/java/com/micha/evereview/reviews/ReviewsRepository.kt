package com.micha.evereview.reviews

import com.micha.evereview.models.*

class ReviewsRepository {
    fun getReviews(onSuccess: (reviews: List<Review<out ReviewItem>>) -> Unit) {
        // TODO: Implement.

        onSuccess(listOf(
            Review(
                Movie("Avengers", 143, 2012),
                7.5,
                "Good film.",
                1
            ),
            Review(
                Movie("The Incredible Hulk", 112, 2008),
                3.0,
                null,
                2
            ),
            Review(
                Series("Agents of S.H.I.E.L.D.", 1, 22),
                8.5,
                "Favorite character: Agent Coulson!",
                3
            ),
            Review(
                Music("Tendon", "Igorrr", "Crazy Shit"),
                7.5,
                "Completely crazy but actually pretty good. Not easy to listen to though.",
                4
            ),
            Review(
                Book("I don't know any books", "Goethe?", "Drama"),
                0.0,
                "Not a book person.",
                5
            )
        ))
    }
}
