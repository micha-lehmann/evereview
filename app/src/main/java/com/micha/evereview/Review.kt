package com.micha.evereview

import com.micha.evereview.models.ReviewItem
import java.util.*

data class Review<T : ReviewItem>(
    val item: T,
    val rating: Double,
    val timestamp: Date,
    val note: String?
)
