package com.micha.evereview.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.micha.evereview.databinding.FragmentEditReviewBinding
import com.micha.evereview.models.Review

class EditReview : NavHostFragment() {
    private val layout by lazy {
        FragmentEditReviewBinding.inflate(layoutInflater)
    }

    private val args: EditReviewArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout.testText.text = args.reviewItem.toString() + args.reviewRating.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(review: Review<*>?) =
            EditReview().apply {
                arguments = Bundle().apply {
                    putSerializable("arg1", review)
                }
            }
    }
}
