package com.no1.taiwan.newsbasket.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.features.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Soon goto the main activity.
        startActivity<MainActivity>()
        finish()
    }
}
