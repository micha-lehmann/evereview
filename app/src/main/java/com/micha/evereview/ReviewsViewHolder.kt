package com.micha.evereview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.micha.evereview.databinding.InfoElementBinding
import com.micha.evereview.databinding.ReviewCardBinding
import com.micha.evereview.models.Movie
import com.micha.evereview.models.Review
import com.micha.evereview.models.ReviewItem

const val MAX_RATING = 10

class ReviewsViewHolder(
    private val view: ReviewCardBinding
) : RecyclerView.ViewHolder(view.root) {
    private val context: Context by lazy {
        view.root.context
    }

    private val infos = listOf(view.info1, view.info2, view.info3, view.info4, view.info5)

    fun fill(review: Review<out ReviewItem>) {
        view.title.text = review.item.title
        view.note.text = review.note

        // TODO: Setting icons is stupid because they can be null.

        setNextInfo(
            context.getString(R.string.rating, review.rating, MAX_RATING),
            AppCompatResources.getDrawable(context, R.drawable.rating)!!,
            context.getString(R.string.info_rating)
        )

        when (review.item) {
            is Movie -> {
                setNextInfo(
                    context.getString(R.string.duration, review.item.duration / 60, review.item.duration % 60),
                    AppCompatResources.getDrawable(context, R.drawable.duration)!!,
                    context.getString(R.string.info_duration)
                )
                setNextInfo(
                    context.getString(R.string.release, review.item.release),
                    AppCompatResources.getDrawable(context, R.drawable.release)!!,
                    context.getString(R.string.info_release)
                )
            }
            else -> {
                Log.w("ReviewsViewHolder", "Received a review with an unknown item type.")
            }
        }
    }

    private fun setNextInfo(text: String, icon: Drawable, iconText: String) {
        val info = findNextInfo() ?: return

        info.icon.setImageDrawable(icon)
        info.icon.contentDescription = iconText
        info.text.text = text
        info.root.visibility = VISIBLE // Important, since if tells findNextInfo that this info is used up.
    }

    private fun findNextInfo(): InfoElementBinding? {
        return infos.find {info -> info.root.visibility == GONE}
    }
}
