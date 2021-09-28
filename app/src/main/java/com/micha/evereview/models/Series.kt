package com.micha.evereview.models

class Series(
    override var title: String,
    var season: Int? = null,
    var episodes: Int? = null
) : ReviewItem() {
    override fun getExtras() = listOf(season, episodes)
    override fun setExtras(extras: Iterable<*>) {
        season = extras.elementAtOrNull(0).toString().toIntOrNull()
        episodes = extras.elementAtOrNull(1).toString().toIntOrNull()
    }
}
