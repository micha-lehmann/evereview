package com.micha.evereview.models

import java.io.Serializable

data class Review<T : ReviewItem>(
    val item: T,
    val rating: Double,
    val note: String?
) : Serializable
