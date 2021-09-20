package com.micha.evereview.models

class Movie(
    override val title: String,
    var duration: Int? = null,
    var release: Int? = null
) : ReviewItem()
