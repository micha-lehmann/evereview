package com.micha.evereview.models

class Music(
    override val title: String,
    var artist: String? = null,
    var musicGenre: String? = null
) : ReviewItem()
