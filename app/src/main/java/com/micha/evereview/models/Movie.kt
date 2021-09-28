package com.micha.evereview.models

class Movie(
    override var title: String,
    var duration: Int? = null,
    var release: Int? = null
) : ReviewItem() {
    override fun getExtras() = listOf(duration, release)
    override fun setExtras(extras: Iterable<*>) {
        duration = extras.elementAtOrNull(0).toString().toIntOrNull()
        release = extras.elementAtOrNull(1).toString().toIntOrNull()
    }
}
