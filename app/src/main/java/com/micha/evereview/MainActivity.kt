package com.micha.evereview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.micha.evereview.databinding.ActivityMainBinding
import com.micha.evereview.edit.EditReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val layout by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.root)

        layout.add.setOnClickListener {
            startActivity(Intent(this, EditReviewActivity::class.java))
        }
    }
}
