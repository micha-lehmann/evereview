package com.micha.evereview.models

class Series(
    override val title: String,
    var season: Int? = null,
    var episodes: Int? = null
) : ReviewItem()
