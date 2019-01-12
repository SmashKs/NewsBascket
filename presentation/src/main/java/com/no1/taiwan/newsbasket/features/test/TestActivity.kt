package com.no1.taiwan.newsbasket.features.test

import android.os.Bundle
import com.devrapid.kotlinknifer.logw
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull

class TestActivity : AdvActivity<TestViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
        observeUnboxNonNull(vm.topNewses) {
            logw(this)
        }
        vm.fetchTopNews()
    }

    override fun provideLayoutId() = R.layout.activity_test_layout
}
