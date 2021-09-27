package com.micha.evereview.reviews

import com.micha.evereview.models.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewsRepository @Inject constructor() {
    private val reviews = mutableListOf(
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
        ),
        Review(
            Movie("Avengers", 143, 2012),
            7.5,
            "Good film.",
            6
        ),
        Review(
            Movie("The Incredible Hulk", 112, 2008),
            3.0,
            null,
            7
        ),
        Review(
            Series("Agents of S.H.I.E.L.D.", 1, 22),
            8.5,
            "Favorite character: Agent Coulson!",
            8
        ),
        Review(
            Music("Tendon", "Igorrr", "Crazy Shit"),
            7.5,
            "Completely crazy but actually pretty good. Not easy to listen to though.",
            9
        ),
        Review(
            Book("I don't know any books", "Goethe?", "Drama"),
            0.0,
            "Not a book person.",
            10
        )
    )

    fun getReviews(onSuccess: (reviews: List<Review<out ReviewItem>>) -> Unit) {
        onSuccess(reviews)
    }

    fun getReview(id: Int): Review<out ReviewItem>? {
        return reviews.find { r -> r.id == id }
    }

    fun updateReview(review: Review<out ReviewItem>): Boolean {
        val index = reviews.indexOfFirst { r -> r.id == review.id }

        if (index == -1) {
            return false
        }

        reviews[index] = review

        return true
    }

    fun addReview(review: Review<out ReviewItem>) {
        reviews.add(Review(review.item, review.rating, review.note, Id.next()))
    }

    class Id {
        companion object {
            private var nextId: Int = 0
            fun next(): Int {
                return ++nextId
            }
        }
    }
}
