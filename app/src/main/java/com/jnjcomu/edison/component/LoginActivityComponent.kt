package com.jnjcomu.edison.component

import android.view.View
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.LoginActivity
import com.jnjcomu.edison.addition.revealWhiteButton
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputLayout

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-10
 */

class LoginActivityComponent : AnkoComponent<LoginActivity> {
    override fun createView(ui: AnkoContext<LoginActivity>): View = with(ui) {
        relativeLayout {
            verticalLayout {
                imageView(R.drawable.logo_white)

                textInputLayout {
                    editText()
                }

                textInputLayout {
                    editText()
                }

                revealWhiteButton("Login") {

                }
            }
        }
    }
}