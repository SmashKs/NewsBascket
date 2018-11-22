package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrapid.dialogbuilder.support.QuickDialogFragment
import com.devrapid.kotlinknifer.loge
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.recyclerview.MultiData
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.ext.doWith
import com.no1.taiwan.newsbasket.ext.happenError
import com.no1.taiwan.newsbasket.ext.observeNonNull
import com.no1.taiwan.newsbasket.ext.peel
import com.no1.taiwan.newsbasket.features.main.viewmodels.KeywordViewModel
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import kotlinx.android.synthetic.main.dialog_input_keyword.view.btn_send
import kotlinx.android.synthetic.main.fragment_keyword.rv_keywords
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.generic.instance

class KeywordFragment : AdvFragment<MainActivity, KeywordViewModel>() {
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    private val keywords: MultiData = cast(mutableListOf(KeywordEntity("hello")))

    //region Base build-in functions
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
    override fun actionBarTitle() = getString(R.string.title_keyword)
    //endregion

    private fun componentSetting() {
        rv_keywords.apply {
            layoutManager = linearLayout
            adapter = MultiTypeAdapter(keywords, context)
        }
    }

    private fun eventSetting() {
//        fab_add.onClick {
//            createKeywordDialog()
//        }
        observeNonNull(vm.keywordsLiveData) {
            peel { logw(it) } happenError { loge(it) } doWith this@KeywordFragment
        }
        vm.fetchKeywords()
    }

    private fun createKeywordDialog() {
        QuickDialogFragment.Builder(this) {
            viewResCustom = R.layout.dialog_input_keyword
            fetchComponents = { v, df ->
                v.btn_send.onClick {
                    df.dismiss()
                }
            }
        }.build().show()
    }
}
