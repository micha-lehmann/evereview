package com.micha.evereview.reviewitems

import java.util.*

class Movie(
    override val title: String,
    var duration: Int,
    var release: Int
) : ReviewItem() {
    override val type = "Movie"
}
