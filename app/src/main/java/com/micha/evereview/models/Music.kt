package com.micha.evereview.models

class Music(
    override var title: String,
    var artist: String? = null,
    var musicGenre: String? = null
) : ReviewItem() {
    override fun getExtras() = listOf(artist, musicGenre)
    override fun setExtras(extras: Iterable<*>) {
        artist = extras.elementAtOrElse(0) { "" }.toString()
        musicGenre = extras.elementAtOrElse(1) { "" }.toString()
    }
}
