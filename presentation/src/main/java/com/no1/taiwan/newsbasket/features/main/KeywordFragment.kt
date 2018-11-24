package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrapid.dialogbuilder.support.QuickDialogFragment
import com.devrapid.kotlinknifer.loge
import com.devrapid.kotlinshaver.isNull
import com.google.android.material.snackbar.Snackbar
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.ext.doWith
import com.no1.taiwan.newsbasket.ext.happenError
import com.no1.taiwan.newsbasket.ext.observeNonNull
import com.no1.taiwan.newsbasket.ext.peel
import com.no1.taiwan.newsbasket.features.main.viewmodels.KeywordViewModel
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.KEYOWRD_ADAPTER
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import kotlinx.android.synthetic.main.dialog_input_keyword.view.btn_send
import kotlinx.android.synthetic.main.dialog_input_keyword.view.et_keyword
import kotlinx.android.synthetic.main.fragment_keyword.fab_add
import kotlinx.android.synthetic.main.fragment_keyword.rv_keywords
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.generic.instance

class KeywordFragment : AdvFragment<MainActivity, KeywordViewModel>() {
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    private val keywordAdapter by instance<NewsAdapter>(KEYOWRD_ADAPTER)

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
            if (layoutManager.isNull())
                layoutManager = linearLayout
            if (adapter.isNull())
                adapter = keywordAdapter
        }
    }

    private fun eventSetting() {
        fab_add.onClick {
            createKeywordDialog()
        }
        observeNonNull(vm.keywordsLiveData) {
            peel {
                keywordAdapter.appendList(it.map { KeywordEntity(it) }.toMutableList())
            } happenError {
                loge(it)
            } doWith this@KeywordFragment
        }
        observeNonNull(vm.storeKeywordLiveData) {
            peel {
                Snackbar.make(fab_add, "success", Snackbar.LENGTH_SHORT).show()
            } happenError {
                Snackbar.make(fab_add, it, Snackbar.LENGTH_SHORT).show()
            } doWith this@KeywordFragment
        }
        vm.fetchKeywords()
    }

    private fun createKeywordDialog() {
        QuickDialogFragment.Builder(this) {
            viewResCustom = R.layout.dialog_input_keyword
            fetchComponents = { v, df ->
                v.btn_send.onClick {
                    vm.storeKeyword(v.et_keyword.text.toString())
                    df.dismiss()
                }
            }
        }.build().show()
    }
}
