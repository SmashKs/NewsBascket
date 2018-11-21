package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import com.devrapid.kotlinknifer.loge
import com.devrapid.kotlinknifer.logw
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.ext.doWith
import com.no1.taiwan.newsbasket.ext.happenError
import com.no1.taiwan.newsbasket.ext.observeNonNull
import com.no1.taiwan.newsbasket.ext.peel

class MainActivity : AdvActivity<MainViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
//        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        observeNonNull(vm.newsLiveData) {
            peel { logw(it) } happenError { loge(it) } doWith this@MainActivity
        }
//        observeNonNull(vm.tokenLiveData) {
//            peel { vm::keepToken } happenError { loge(it) } doWith this@MainActivity
//        }
    }

    override fun provideLayoutId() = R.layout.activity_main
}
