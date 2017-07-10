package com.jnjcomu.edison.addition

import android.app.Activity
import com.jnjcomu.edison.EdisonApplication

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-10
 */

val Activity.inApplication: EdisonApplication
    get() = this.application as EdisonApplication