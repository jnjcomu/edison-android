package com.jnjcomu.edison.factory;

import android.view.animation.Interpolator;

import com.jnjcomu.edison.ui.LogoInterpolator;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-27
 */

public class InterpolatorFactory {
    public static Interpolator getDefaultLogoInterpolator() {
        return new LogoInterpolator(
                LogoInterpolator.DEFAULT_AMPLITUDE,
                LogoInterpolator.DEFAULT_FREQUENCY
        );
    }
}
