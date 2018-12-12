package com.no1.taiwan.newsbasket.features.splash

import android.os.Bundle
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.BaseActivity
import com.no1.taiwan.newsbasket.features.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    override fun init(savedInstanceState: Bundle?) {
        startActivity<MainActivity>()
        finish()
    }

    override fun provideLayoutId() = R.layout.activity_splash
}
