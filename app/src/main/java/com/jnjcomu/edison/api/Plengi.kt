package com.jnjcomu.edison.api

import android.content.Context
import com.loplat.placeengine.Plengi

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-09
 */

val Context.plengi: Plengi
    get() = Plengi.getInstance(this)