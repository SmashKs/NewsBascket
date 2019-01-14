package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import android.view.Menu
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.features.main.viewmodels.MainViewModel

class MainActivity : AdvActivity<MainViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
    }

    override fun provideLayoutId() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_index, menu)

        return true
    }
}
