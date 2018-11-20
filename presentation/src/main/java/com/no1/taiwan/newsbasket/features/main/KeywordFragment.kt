package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.BaseFragment

class KeywordFragment : BaseFragment<MainActivity>() {
    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.fragment_keyword

}
