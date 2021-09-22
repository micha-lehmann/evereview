package com.micha.evereview

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.micha.evereview.databinding.ActivityMainBinding
import com.micha.evereview.edit.EditReviewActivity
import com.micha.evereview.reviews.ReviewsAdapter
import com.micha.evereview.reviews.ReviewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val layout by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val model by viewModels<ReviewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.root)

        layout.add.setOnClickListener {
            // No need to pass extras, because they default to null.
            startActivity(Intent(this, EditReviewActivity::class.java))
        }

        layout.reviews.layoutManager = LinearLayoutManager(this)
        model.reviews.observe(this) { reviews ->
            layout.reviews.adapter = ReviewsAdapter(reviews)
        }
        model.loadReviews()
    }
}
