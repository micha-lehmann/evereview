package com.micha.evereview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.micha.evereview.databinding.ActivityMainBinding
import com.micha.evereview.reviews.ReviewsViewModel

class MainActivity : AppCompatActivity() {
    private val layout by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val model by viewModels<ReviewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenu = layout.mainMenu
    }
}
