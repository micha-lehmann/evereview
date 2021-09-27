package com.micha.evereview.models

class Series(
    override var title: String,
    var season: Int? = null,
    var episodes: Int? = null
) : ReviewItem()
