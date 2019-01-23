package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import com.no1.taiwan.newsbasket.App
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.viewpager.FragmentViewPagerAdapter
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull
import com.no1.taiwan.newsbasket.features.main.subfragments.ArticleFragment
import com.no1.taiwan.newsbasket.features.main.viewmodels.IndexViewModel
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.IndexModule
import kotlinx.android.synthetic.main.fragment_index.btn_archive
import kotlinx.android.synthetic.main.fragment_index.btn_next
import kotlinx.android.synthetic.main.fragment_news.vp_news
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.kodein.di.Kodein

class IndexFragment : AdvFragment<MainActivity, IndexViewModel>() {
    override val kodein = Kodein.lazy {
        extend(super.kodein)
        import(IndexModule.indexProvider())
    }
    //region Base build-in functions
    /** The block of binding to [androidx.lifecycle.ViewModel]'s [androidx.lifecycle.LiveData]. */
    override fun bindLiveData() {
        observeUnboxNonNull(vm.tokenLiveData) {
            App.isFirstTimeOpen = true
        }
        observeUnboxNonNull(vm.topNewses) {
            setViewPagerAdapter(this)
        }
    }

    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
        if (!App.isFirstTimeOpen) {
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                vm.addFirstSubscriber(it.token)
            }
        }
//        vm.fetchTopNews()
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

    private fun setViewPagerAdapter(articles: Articles) {
        val adapter = FragmentViewPagerAdapter(requireFragmentManager(),
                                               articles.map(ArticleFragment.Factory::newInstance))
        vp_news.adapter = adapter
    }
}
