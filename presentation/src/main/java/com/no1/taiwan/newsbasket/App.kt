package com.no1.taiwan.newsbasket

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.devrapid.kotlinknifer.SharedPrefs
import com.facebook.stetho.Stetho
import com.no1.taiwan.newsbasket.internal.di.RepositoryModule.repositoryProvider
import com.no1.taiwan.newsbasket.internal.di.UtilModule.utilProvider
import com.no1.taiwan.newsbasket.internal.di.dependency.UsecaseModule.usecaseProvider
import org.jetbrains.anko.defaultSharedPreferences
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

/**
 * Android Main Application
 */
class App : MultiDexApplication(), KodeinAware {
    companion object {
        var isFirstTimeOpen = false
        lateinit var appContext: Context
            private set
    }

    init {
        appContext = this
    }

    /**
     * A Kodein Aware class must be within reach of a Kodein object.
     */
    override val kodein = lazy {
        val app = this@App

        import(androidXModule(app))
        /** bindings */
        import(utilProvider(app))
        /** usecases are bind here but the scope is depending on each layers.  */
        import(usecaseProvider())
        import(repositoryProvider(app))
    }

    override fun onCreate() {
        super.onCreate()

        // key-value storage, choose one for using.
        SharedPrefs.setPrefSettings(defaultSharedPreferences)
        Stetho.initializeWithDefaults(this)
    }
}
