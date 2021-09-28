package com.micha.evereview.models

class Book(
    override var title: String,
    var author: String? = null,
    var bookGenre: String? = null
) : ReviewItem() {
    override fun getExtras() = listOf(author, bookGenre)
    override fun setExtras(extras: Iterable<*>) {
        author = extras.elementAtOrElse(0) { "" }.toString()
        bookGenre = extras.elementAtOrElse(1) { "" }.toString()
    }
}
