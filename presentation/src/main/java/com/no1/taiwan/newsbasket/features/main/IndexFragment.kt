package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.features.main.viewmodels.IndexViewModel
import kotlinx.android.synthetic.main.fragment_index.btn_next
import org.jetbrains.anko.sdk25.coroutines.onClick

class IndexFragment : AdvFragment<MainActivity, IndexViewModel>() {
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
    override fun provideInflateView() = R.layout.fragment_index

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = getString(R.string.app_name)
    //endregion

    private fun componentSetting() {
        vm.addSubscriber("")
    }

    private fun eventSetting() {
        btn_next.onClick {
            findNavController().navigate(R.id.action_nav_index_to_keyword)
        }
    }
}
