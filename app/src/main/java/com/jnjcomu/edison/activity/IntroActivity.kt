package com.jnjcomu.edison.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jnjcomu.edison.R
import com.jnjcomu.edison.fragment.TermsFragment
import com.jnjcomu.edison.fragment.TipFragment
import com.jnjcomu.edison.fragment.WelcomeFragment
import com.jnjcomu.edison.ui.FragmentAdapter
import kotlinx.android.synthetic.main.activity_intro.*

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initView()
    }

    fun initView() {
        val mAdapter = FragmentAdapter(supportFragmentManager)

        mAdapter.addFragment(WelcomeFragment())
        mAdapter.addFragment(TipFragment())
        mAdapter.addFragment(TermsFragment())
        viewPager.adapter = mAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position==2){
                    navi.visibility = View.GONE
                } else {
                    navi.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

    }

    fun skip(v: View) {
        viewPager.currentItem = 3
    }

    fun next(v: View) {
        viewPager.currentItem = viewPager.currentItem + 1
    }

}