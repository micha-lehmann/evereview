package com.micha.evereview.reviews

import android.content.Context
import android.util.Log
import android.view.View.GONE
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.*

const val MAX_RATING = 10

class ReviewsViewHolder(
    private val view: ReviewCardBinding
) : RecyclerView.ViewHolder(view.root) {
    private val context: Context by lazy {
        view.root.context
    }

    fun fill(review: Review<out ReviewItem>) {
        view.title.text = review.item.title

        if (review.note == null) {view.note.visibility = GONE}
        else {view.note.text = review.note}


        addInfo(
            context.getString(R.string.rating, review.rating, MAX_RATING),
            R.drawable.rating
        )

        when (review.item) {
            is Movie -> {
                addInfo(
                    context.getString(R.string.duration, review.item.duration / 60, review.item.duration % 60),
                    R.drawable.duration
                )
                addInfo(
                    context.getString(R.string.release, review.item.release),
                    R.drawable.release
                )
            }
            else -> {
                Log.w("ReviewsViewHolder (new)", "Received a review with an unknown item type.")
            }
        }
    }

    private fun addInfo(text: String, icon: Int) {
        val info = Chip(context)

        info.setChipIconSizeResource(R.dimen.regular_text)
        info.iconStartPadding = (24 - info.chipIconSize / context.resources.displayMetrics.density) // 24 is the maximum icon size in a chip

        info.text = text
        info.setChipIconResource(icon)

        view.infos.addView(info)
    }
}
