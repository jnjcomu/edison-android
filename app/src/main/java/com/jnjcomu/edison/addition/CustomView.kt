package com.jnjcomu.edison.addition

import android.view.ViewManager
import android.widget.Button
import org.jetbrains.anko.custom.ankoView

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-10
 */

fun ViewManager.revealWhiteButton(text: String, init: Button.() -> Unit) =
        ankoView({ Button(it, null) }, 0) {
            setText(text)
            init()
        }