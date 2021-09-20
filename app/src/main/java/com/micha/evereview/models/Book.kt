package com.micha.evereview.models

class Book(
    override val title: String,
    var author: String? = null,
    var bookGenre: String? = null
) : ReviewItem()
