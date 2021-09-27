package com.micha.evereview.models

class Movie(
    override var title: String,
    var duration: Int? = null,
    var release: Int? = null
) : ReviewItem()
