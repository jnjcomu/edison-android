package com.jnjcomu.edison.ui

import android.content.Context
import android.support.annotation.AnimRes
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.TextView

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-12
 */

fun View.startAnim(context: Context, @AnimRes animRes: Int) {
    this.startAnimation(AnimationUtils.loadAnimation(context, animRes))
}

fun View.startAnim(context: Context, @AnimRes animRes: Int, interpolator: Interpolator) {
    val anim = AnimationUtils.loadAnimation(context, animRes)
    anim.interpolator = interpolator

    this.startAnimation(anim)
}

fun View.cancelAnim() {
    this.animation?.cancel()
}

fun View.restartAnim(context: Context, @AnimRes animRes: Int) {
    cancelAnim()
    startAnim(context, animRes)
}

fun View.restartAnim(context: Context, @AnimRes animRes: Int, interpolator: Interpolator) {
    cancelAnim()
    startAnim(context, animRes, interpolator)
}
