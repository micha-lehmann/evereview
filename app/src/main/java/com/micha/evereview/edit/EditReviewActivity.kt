package com.micha.evereview.edit

import android.os.Bundle
import android.text.InputType.*
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.micha.evereview.R
import com.micha.evereview.databinding.ActivityEditReviewBinding
import com.micha.evereview.models.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditReviewActivity @Inject constructor() : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {
    private val layout by lazy {
        ActivityEditReviewBinding.inflate(layoutInflater)
    }

    private val model by viewModels<EditReviewViewModel>()

    private val reviewId by lazy {
        intent.extras?.getInt("reviewId")
    }

    private lateinit var review: Review<ReviewItem>
    private var newReview = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.review_item_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            layout.categorySelect.adapter = adapter
        }

        layout.categorySelect.onItemSelectedListener = this

        layout.saveButton.setOnClickListener {
            save()
            finish()
        }

        Log.i("EditReviewActivity", "reviewId = $reviewId")

        if (reviewId == null) {
            newReview = true
            review = Review(Movie(""), 0.0, null, 0)
        } else {
            review = model.getReview(reviewId!!) as Review<ReviewItem>? ?: return
        }

        addSpecificInputs(review.item)
    }

    private fun save() {
        model.editReview(review)

        // TODO: What happens when a new review should be added?
    }

    private fun clearSpecificInputs() {
        layout.specificInputs.removeAllViews()
    }

    private fun addSpecificInputs(reviewItem: ReviewItem) {
        val inputs: Map<String, Int> = when (reviewItem) {
            // TODO: These are not localized.
            is Movie -> mapOf(
                Pair("Duration", TYPE_CLASS_DATETIME or TYPE_DATETIME_VARIATION_TIME),
                Pair("Release", TYPE_CLASS_DATETIME or TYPE_DATETIME_VARIATION_DATE)
            )
            is Series -> mapOf(
                Pair("Season", TYPE_CLASS_NUMBER),
                Pair("Episodes", TYPE_CLASS_NUMBER)
            )
            is Music -> mapOf(
                Pair("Artist", TYPE_CLASS_TEXT),
                Pair("Genre", TYPE_CLASS_TEXT)
            )
            is Book -> mapOf(
                Pair("Author", TYPE_CLASS_TEXT),
                Pair("Genre", TYPE_CLASS_TEXT)
            )
            else -> mapOf()
        }

        for ((label, type) in inputs) {
            val input = EditText(this)
            input.inputType = type
            input.hint = label
            layout.specificInputs.addView(input)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        when (pos) {
            0 -> review.item = Movie(review.item.title)
            1 -> review.item = Series(review.item.title)
            2 -> review.item = Music(review.item.title)
            3 -> review.item = Book(review.item.title)
        }

        clearSpecificInputs()
        addSpecificInputs(review.item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {} /* no-op */
}
