package com.jnjcomu.edison.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-04
 */

class FragmentAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragments = ArrayList<Fragment>()

    fun addFragment(mFragment: Fragment) {
        mFragments.add(mFragment)
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}