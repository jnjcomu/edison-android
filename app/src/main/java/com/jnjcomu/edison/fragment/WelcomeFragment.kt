package com.jnjcomu.edison.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.startAnim
import com.jnjcomu.edison.factory.InterpolatorFactory

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        view!!.findViewById<View>(R.id.img_logo).startAnim(
                activity!!, R.anim.logo_scale,
                InterpolatorFactory.makeLogoInterpolator()
        )

        return view
    }

}