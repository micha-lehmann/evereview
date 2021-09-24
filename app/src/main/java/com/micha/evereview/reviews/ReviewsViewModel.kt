package com.micha.evereview.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repo: ReviewsRepository

    private val _reviews = MutableLiveData<List<Review<out ReviewItem>>>()
    val reviews: LiveData<List<Review<out ReviewItem>>> = _reviews

    fun loadReviews() {
        repo.getReviews { reviews ->
            _reviews.postValue(reviews)
        }
    }
}
