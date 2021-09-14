package com.micha.evereview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.micha.evereview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val layout by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenu = layout.mainMenu
    }
}
