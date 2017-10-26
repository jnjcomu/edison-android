package com.jnjcomu.edison.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatCheckBox
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import com.jnjcomu.edison.R
import com.jnjcomu.edison.activity.CheckInActivity

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class TermsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_terms, container, false)

        view!!.findViewById<WebView>(R.id.terms_1).loadUrl("file:///android_asset/terms.html")
        view.findViewById<WebView>(R.id.terms_2).loadUrl("file:///android_asset/terms_privacy.html")

        view.findViewById<View>(R.id.start).setOnClickListener{
            if(view.findViewById<AppCompatCheckBox>(R.id.agree_1).isChecked &&
                    view.findViewById<AppCompatCheckBox>(R.id.agree_2).isChecked) {
                startActivity(Intent(activity, CheckInActivity::class.java))
            } else {
                Toast.makeText(activity,"약관에 모두 동의해야 Edison 서비스를 이용하실 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}