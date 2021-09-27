package com.micha.evereview

import com.google.android.material.slider.Slider

class SliderValueChangeWatcher(
    private val fn: (Float) -> Unit
) : Slider.OnChangeListener {
    override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        fn(value)
    }
}
