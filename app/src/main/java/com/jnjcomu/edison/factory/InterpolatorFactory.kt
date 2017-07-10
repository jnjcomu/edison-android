package com.jnjcomu.edison.factory

import com.jnjcomu.edison.ui.LogoInterpolator

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-27
 */

object InterpolatorFactory {
    fun makeLogoInterpolator() = LogoInterpolator(
            LogoInterpolator.DEFAULT_AMPLITUDE,
            LogoInterpolator.DEFAULT_FREQUENCY
    )
}
