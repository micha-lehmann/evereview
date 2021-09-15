package com.micha.evereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem

class ReviewsViewModel(
    private val repo: ReviewsRepository = ReviewsRepository()
) : ViewModel() {
    private val _reviews = MutableLiveData<List<Review<out ReviewItem>>>()
    val reviews: LiveData<List<Review<out ReviewItem>>> = _reviews

    fun loadReviews() {
        repo.getReviews { reviews ->
            _reviews.postValue(reviews)
        }
    }
}
