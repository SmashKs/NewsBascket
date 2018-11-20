package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import kotlinx.android.synthetic.main.activity_main.navigation

class MainActivity : AdvActivity<MainViewModel>() {
    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_book -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun init(savedInstanceState: Bundle?) {
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
//        observeNonNull(vm.newsLiveData) {
//            peel { logw(it) } happenError { loge(it) } doWith this@MainActivity
//        }
//        observeNonNull(vm.tokenLiveData) {
//            peel { vm::keepToken } happenError { loge(it) } doWith this@MainActivity
//        }
    }

    override fun provideLayoutId() = R.layout.activity_main
}
