package com.micha.evereview

import android.text.Editable
import android.text.TextWatcher

class TextChangeWatcher(
    private val fn: (String) -> Unit
) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        fn(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {}
}
