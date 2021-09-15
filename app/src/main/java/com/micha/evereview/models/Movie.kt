package com.micha.evereview.models

class Movie(
    override val title: String,
    var duration: Int,
    var release: Int
) : ReviewItem() {
    override val type = "Movie"
}
