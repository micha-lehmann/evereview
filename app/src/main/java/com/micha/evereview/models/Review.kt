package com.micha.evereview.models

import java.util.*

data class Review<T : ReviewItem>(
    val item: T,
    val rating: Double,
    val timestamp: Date,
    val note: String?
)
