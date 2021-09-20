package com.micha.evereview.models

class Music(
    override val title: String,
    val artist: String? = null,
    val musicGenre: String? = null
) : ReviewItem()
