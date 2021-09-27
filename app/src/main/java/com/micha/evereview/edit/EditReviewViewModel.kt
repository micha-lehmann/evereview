package com.micha.evereview.edit

import androidx.lifecycle.ViewModel
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import com.micha.evereview.reviews.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditReviewViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repo: ReviewsRepository

    fun getReview(id: Int): Review<out ReviewItem>? {
        return repo.getReview(id)?.copy()
    }

    fun editReview(review: Review<out ReviewItem>) {
        repo.updateReview(review)
    }

    fun addReview(review: Review<out ReviewItem>) {
        repo.addReview(review)
    }
}
