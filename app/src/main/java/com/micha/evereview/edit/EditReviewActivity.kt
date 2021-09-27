package com.micha.evereview.edit

import android.os.Bundle
import android.text.Editable
import android.text.InputType.*
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.micha.evereview.R
import com.micha.evereview.databinding.ActivityEditReviewBinding
import com.micha.evereview.models.*
import com.micha.evereview.reviews.ReviewItemType.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditReviewActivity @Inject constructor() : AppCompatActivity(),
    AdapterView.OnItemSelectedListener, Slider.OnChangeListener, TextWatcher {
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

        layout.title.addTextChangedListener(this)

        layout.rating.addOnChangeListener(this)

        layout.saveButton.setOnClickListener {
            save()
            finish()
        }

        if (reviewId == null) {
            newReview = true
            review = Review(Movie(""), 0.0, null, 0)
        } else {
            review = model.getReview(reviewId!!) as Review<ReviewItem>? ?: return
        }

        setCategorySelect(review.item) // Calls addSpecificInputs through onItemSelected.

        layout.rating.value = review.rating.toFloat()
    }

    private fun save() {
        if (newReview) {
            model.addReview(review)
        } else {
            model.editReview(review)
        }
    }

    private fun setCategorySelect(reviewItem: ReviewItem) {
        val pos = when (reviewItem) {
            is Movie -> MOVIE.ordinal
            is Series -> SERIES.ordinal
            is Music -> MUSIC.ordinal
            is Book -> BOOK.ordinal
            else -> UNKNOWN.ordinal
        }

        layout.categorySelect.setSelection(pos - 1)
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
        when (pos + 1) {
            MOVIE.ordinal -> review.item = Movie(review.item.title)
            SERIES.ordinal -> review.item = Series(review.item.title)
            MUSIC.ordinal -> review.item = Music(review.item.title)
            BOOK.ordinal -> review.item = Book(review.item.title)
        }

        clearSpecificInputs()
        addSpecificInputs(review.item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {} /* no-op */

    override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        review.rating = value.toDouble()
    }

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        review.item.title = text.toString()
    }

    override fun afterTextChanged(p0: Editable?) {}
}
