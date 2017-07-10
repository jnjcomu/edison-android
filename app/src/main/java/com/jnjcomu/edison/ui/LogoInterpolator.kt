package com.jnjcomu.edison.ui

import android.view.animation.Interpolator

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-12
 */

class LogoInterpolator(amplitude: Double, frequency: Double) : Interpolator {

    private var mAmplitude = 1.0
    private var mFrequency = 10.0

    init {
        mAmplitude = amplitude
        mFrequency = frequency
    }

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1).toFloat()
    }

    companion object {
        val DEFAULT_AMPLITUDE = 0.2
        val DEFAULT_FREQUENCY = 18.0

        private val defaultLogoInterpolator: LogoInterpolator? = null
    }
}