package com.micha.evereview.reviews

import android.content.Context
import android.view.View.GONE
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.micha.evereview.R
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem

const val MAX_RATING = 10

open class ReviewsViewHolder(
    private val view: ReviewCardBinding
) : RecyclerView.ViewHolder(view.root) {
    protected val context: Context by lazy {
        view.root.context
    }

    open fun clear() {
        view.title.text = ""
        view.infos.removeAllViews()
    }

    open fun fill(review: Review<out ReviewItem>) {
        view.title.text = review.item.title

        if (review.note == null) {
            view.note.visibility = GONE
        } else {
            view.note.text = review.note
        }

        addInfo(
            context.getString(R.string.rating, review.rating, MAX_RATING),
            R.drawable.rating
        )
    }

    protected fun addInfo(text: String, icon: Int) {
        val info = Chip(context)

        info.setChipIconSizeResource(R.dimen.regular_text)
        info.iconStartPadding =
            (24 - info.chipIconSize / context.resources.displayMetrics.density) // 24 is the maximum icon size in a chip

        info.text = text
        info.setChipIconResource(icon)

        view.infos.addView(info)
    }
}
