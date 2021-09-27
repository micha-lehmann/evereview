package com.micha.evereview.models

class Book(
    override var title: String,
    var author: String? = null,
    var bookGenre: String? = null
) : ReviewItem()
