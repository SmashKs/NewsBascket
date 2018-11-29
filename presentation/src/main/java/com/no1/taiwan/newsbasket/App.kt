package com.no1.taiwan.newsbasket

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.no1.taiwan.newsbasket.internal.di.AppModule.appProvider
import com.no1.taiwan.newsbasket.internal.di.RecyclerViewModule.recyclerViewProvider
import com.no1.taiwan.newsbasket.internal.di.RepositoryModule.repositoryProvider
import com.no1.taiwan.newsbasket.internal.di.ServiceModule.serviceProvider
import com.no1.taiwan.newsbasket.internal.di.UtilModule.utilProvider
import com.no1.taiwan.newsbasket.internal.di.dependency.UsecaseModule.usecaseProvider
import com.no1.taiwan.newsbasket.services.InitialService
import org.jetbrains.anko.startService
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
        import(appProvider())
        import(utilProvider(app))
        /** activities or fragments */
        import(recyclerViewProvider(app))
        /** usecases are bind here but the scope is depending on each layers.  */
        import(usecaseProvider())
        import(repositoryProvider())
        import(serviceProvider(app))
    }

    override fun onCreate() {
        super.onCreate()

        // Start init process service.
        startService<InitialService>()
    }
}
