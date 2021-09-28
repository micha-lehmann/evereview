package com.micha.evereview.edit

import android.os.Bundle
import android.text.InputType.*
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.micha.evereview.R
import com.micha.evereview.SliderValueChangeWatcher
import com.micha.evereview.TextChangeWatcher
import com.micha.evereview.databinding.ActivityEditReviewBinding
import com.micha.evereview.models.*
import com.micha.evereview.reviews.ReviewItemType.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.pow

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

        if (reviewId == null) {
            newReview = true
            review = Review(Movie(""), 0.0, null, 0)
        } else {
            @Suppress("UNCHECKED_CAST")
            review = model.getReview(reviewId!!) as Review<ReviewItem>? ?: return
        }

        layout.title.setText(review.item.title)

        layout.rating.value = review.rating.toFloat()

        layout.categorySelect.onItemSelectedListener = this

        layout.title.addTextChangedListener(TextChangeWatcher { text ->
            review.item.title = text
        })

        layout.rating.addOnChangeListener(SliderValueChangeWatcher { value ->
            review.rating = value.toDouble()
        })

        layout.saveButton.setOnClickListener {
            save()
            finish()
        }

        setCategorySelect(review.item) // Calls addSpecificInputs through onItemSelected.
    }

    private fun save() {
        val inputs = layout.specificInputs.children
            .map { v -> v as EditText }
            .map { e -> e.text.toString() }
            .toList()

        when (val item = review.item) {
            is Movie -> {
                item.duration = durationToMinutes(inputs[0])
                item.release = inputs[1].toIntOrNull()
            }
            is Series -> {
                item.season = inputs[0].toIntOrNull()
                item.episodes = inputs[1].toIntOrNull()
            }
            is Music -> {
                item.artist = inputs[0]
                item.musicGenre = inputs[1]
            }
            is Book -> {
                item.author = inputs[0]
                item.bookGenre = inputs[1]
            }
        }

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
        // TODO: This makes my brain hurt.
        val inputs: Map<String, Pair<Int, Any?>> = when (reviewItem) {
            // TODO: These are not localized.
            is Movie -> mapOf(
                Pair(
                    "Duration", Pair(
                        TYPE_CLASS_DATETIME or TYPE_DATETIME_VARIATION_TIME,
                        reviewItem.duration
                    )
                ),
                Pair(
                    "Release", Pair(
                        TYPE_CLASS_DATETIME or TYPE_DATETIME_VARIATION_DATE,
                        reviewItem.release
                    )
                )
            )
            is Series -> mapOf(
                Pair("Season", Pair(TYPE_CLASS_NUMBER, reviewItem.season)),
                Pair("Episodes", Pair(TYPE_CLASS_NUMBER, reviewItem.episodes))
            )
            is Music -> mapOf(
                Pair("Artist", Pair(TYPE_CLASS_TEXT, reviewItem.artist)),
                Pair("Genre", Pair(TYPE_CLASS_TEXT, reviewItem.musicGenre))
            )
            is Book -> mapOf(
                Pair("Author", Pair(TYPE_CLASS_TEXT, reviewItem.author)),
                Pair("Genre", Pair(TYPE_CLASS_TEXT, reviewItem.bookGenre))
            )
            else -> mapOf()
        }

        for ((label, info) in inputs) {
            val (type, default) = info
            val input = EditText(this)
            input.inputType = type
            input.hint = label
            input.setText((default ?: "").toString())
            layout.specificInputs.addView(input)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        val extras = review.item.getExtras()
        when (pos + 1) {
            MOVIE.ordinal -> review.item = Movie(review.item.title)
            SERIES.ordinal -> review.item = Series(review.item.title)
            MUSIC.ordinal -> review.item = Music(review.item.title)
            BOOK.ordinal -> review.item = Book(review.item.title)
        }
        review.item.setExtras(extras)

        clearSpecificInputs()
        addSpecificInputs(review.item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {} /* no-op */

    private fun durationToMinutes(duration: String): Int { // jank
        var result = 0
        val components = duration.split(':')
        for (i in components.indices) {
            result += (components[i].toIntOrNull()
                ?: 0) * 60.0.pow(components.size - i.toDouble() - 1).toInt()
        }

        return result
    }
}
