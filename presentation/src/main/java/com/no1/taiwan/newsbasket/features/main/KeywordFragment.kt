package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.BaseFragment
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import kotlinx.android.synthetic.main.fragment_keyword.fab_add
import kotlinx.android.synthetic.main.fragment_keyword.rv_keywords
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.generic.instance

class KeywordFragment : BaseFragment<MainActivity>() {
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
        componentSetting()
        eventSetting()
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.fragment_keyword

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = "Keywords"

    private fun componentSetting() {
        rv_keywords.apply {
            layoutManager = linearLayout
//            adapter =
        }
    }

    private fun eventSetting() {
        fab_add.onClick {
            createKeywordDialog()
        }
    }

    private fun createKeywordDialog() {
    }
}
