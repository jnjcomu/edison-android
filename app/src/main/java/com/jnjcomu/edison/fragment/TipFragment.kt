package com.jnjcomu.edison.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jnjcomu.edison.R

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class TipFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_tip, container, false)

        return view
    }

}