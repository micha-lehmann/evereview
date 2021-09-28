package com.micha.evereview.models

import java.io.Serializable

abstract class ReviewItem : Serializable {
    abstract var title: String

    abstract fun getExtras(): Iterable<*>
    abstract fun setExtras(extras: Iterable<*>)
}
