package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.features.main.viewmodels.MainViewModel

class MainActivity : AdvActivity<MainViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
    }

    override fun provideLayoutId() = R.layout.activity_main
}
