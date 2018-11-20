package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_index.tv_hello
import org.jetbrains.anko.sdk25.coroutines.onClick

class IndexFragment : BaseFragment<MainActivity>() {
    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
        tv_hello.onClick {
            findNavController().navigate(R.id.action_nav_index_to_keyword)
        }
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.fragment_index

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = "News"

}
