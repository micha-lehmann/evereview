package com.micha.evereview.models

import java.io.Serializable

abstract class ReviewItem : Serializable {
    abstract val type: String
    abstract val title: String
}
