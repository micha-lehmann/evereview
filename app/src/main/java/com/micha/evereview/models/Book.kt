package com.micha.evereview.models

class Book(
    override val title: String,
    val author: String? = null,
    val bookGenre: String? = null
) : ReviewItem()
