package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import com.no1.taiwan.newsbasket.App
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.features.main.viewmodels.IndexViewModel
import kotlinx.android.synthetic.main.fragment_index.btn_archive
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
        if (!App.isFirstTimeOpen)
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                vm.addFirstSubscriber(it.token)
                App.isFirstTimeOpen = true
            }
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all component listeners here.
     */
    override fun componentListenersBinding() {
        btn_next.onClick {
            findNavController().navigate(R.id.action_nav_index_to_keyword)
        }
        btn_archive.onClick {
            findNavController().navigate(R.id.action_nav_index_to_archive)
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
    override fun actionBarTitle() = getString(R.string.app_name)
    //endregion
}
