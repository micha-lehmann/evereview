package com.micha.evereview.reviewitems

class Series(
    override val title: String,
    var season: Int,
    var episodes: Int
) : ReviewItem() {
    override val type = "Series"
}
