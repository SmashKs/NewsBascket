package com.no1.taiwan.newsbasket.components.viewpager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.no1.taiwan.newsbasket.bases.BaseActivity
import com.no1.taiwan.newsbasket.bases.BaseFragment

class FragmentViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: List<BaseFragment<BaseActivity>>
) : FragmentPagerAdapter(fragmentManager) {
    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int) = fragments[position]

    /**
     * Return the number of views available.
     */
    override fun getCount() = fragments.size
}
