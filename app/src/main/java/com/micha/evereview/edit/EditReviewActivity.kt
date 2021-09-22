package com.micha.evereview.edit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.micha.evereview.R
import com.micha.evereview.databinding.ActivityEditReviewBinding
import com.micha.evereview.models.*

class EditReviewActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val layout by lazy {
        ActivityEditReviewBinding.inflate(layoutInflater)
    }

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
    }

    private fun clearSpecificInputs() {
        layout.specificInputs.removeAllViews()
    }

    private fun addSpecificInputs(reviewItem: ReviewItem) {
        val amount = when (reviewItem) {
            is Movie -> 2
            is Series -> 2
            is Music -> 2
            is Book -> 2
            else -> 0
        }

        for (i in 1..amount) {
            Log.i("EditReviewActivity", "Add one input.")
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {} /* no-op */
}
