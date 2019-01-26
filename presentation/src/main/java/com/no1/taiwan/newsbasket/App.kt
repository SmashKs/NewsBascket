package com.no1.taiwan.newsbasket

import android.content.Context
import androidx.multidex.MultiDexApplication
import androidx.work.WorkManager
import com.no1.taiwan.newsbasket.internal.di.RepositoryModule.repositoryProvider
import com.no1.taiwan.newsbasket.internal.di.UtilModule.dataUtilProvider
import com.no1.taiwan.newsbasket.internal.di.UtilModule.utilProvider
import com.no1.taiwan.newsbasket.internal.di.dependency.UsecaseModule.usecaseProvider
import com.no1.taiwan.newsbasket.services.workers.WorkerRequestFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.instance

/**
 * Android Main Application
 */
class App : MultiDexApplication(), KodeinAware {
    companion object {
        var isFirstTimeOpen = false
        lateinit var appContext: Context
            private set
    }

    private val workManager by instance<WorkManager>()
    private val initRequest by lazy { WorkerRequestFactory.getWorkerRequest(WorkerRequestFactory.WORKER_INIT) }

    init {
        appContext = this
    }

    /**
     * A Kodein Aware class must be within reach of a Kodein object.
     */
    override val kodein = Kodein.lazy {
        val app = this@App

        import(androidXModule(app))
        /** bindings */
        import(utilProvider(app))
        /** usecases are bind here but the scope is depending on each layers.  */
        import(usecaseProvider())
        import(repositoryProvider(app))
        import(dataUtilProvider())
    }

    override fun onCreate() {
        super.onCreate()

        workManager.enqueue(initRequest)
    }
}
