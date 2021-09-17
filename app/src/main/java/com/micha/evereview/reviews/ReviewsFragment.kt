package com.micha.evereview.reviews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.micha.evereview.databinding.ReviewsFragmentBinding

class ReviewsFragment : Fragment() {
    companion object {
        fun newInstance() = ReviewsFragment()
    }

    private val layout by lazy {
        ReviewsFragmentBinding.inflate(layoutInflater)
    }

    private val model by activityViewModels<ReviewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout.reviews.layoutManager = LinearLayoutManager(context)
        model.reviews.observe(this) { reviews ->
            layout.reviews.adapter = ReviewsAdapter(reviews)
        }
        model.loadReviews()
    }
}
