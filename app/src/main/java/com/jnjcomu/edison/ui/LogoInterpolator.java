package com.jnjcomu.edison.ui;

import android.view.animation.Interpolator;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-05-12
 */

public class LogoInterpolator implements Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    public LogoInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}