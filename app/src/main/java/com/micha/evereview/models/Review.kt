package com.micha.evereview.models

import java.io.Serializable

data class Review<T : ReviewItem>(
    var item: T,
    var rating: Double,
    var note: String?,
    var id: Int
) : Serializable
