package com.jnjcomu.edison.ui

import android.content.Context
import android.support.annotation.AnimRes
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator

import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

/**
 * @author kimwoojae <wj1187></wj1187>@naver.com>
 * *
 * @since 2017-05-12
 */

class AnimationManager private constructor(private val context: Context) {

    fun startAnim(view: View, @AnimRes animRes: Int) {
        val anim = AnimationUtils.loadAnimation(context, animRes)

        view.startAnimation(anim)
    }

    fun startAnim(view: View, @AnimRes animRes: Int, interpolator: Interpolator) {
        val anim = AnimationUtils.loadAnimation(context, animRes)
        anim.interpolator = interpolator

        view.startAnimation(anim)
    }

    fun restartAnim(view: View, @AnimRes animRest: Int) {
        cancelAnim(view)
        startAnim(view, animRest)
    }

    fun restartAnim(view: View, @AnimRes animRest: Int, interpolator: Interpolator) {
        cancelAnim(view)
        startAnim(view, animRest, interpolator)
    }

    fun cancelAnim(v: View) {
        val anim = v.animation
        anim?.cancel()
    }

    companion object {
        private var instance: AnimationManager? = null

        fun getInstance(context: Context): AnimationManager {
            if (instance == null) instance = AnimationManager(context)

            return instance!!
        }
    }
}
