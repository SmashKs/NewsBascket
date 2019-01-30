package com.no1.taiwan.newsbasket.services.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListCase
import com.no1.taiwan.newsbasket.ext.exec
import com.no1.taiwan.newsbasket.ext.internet.AdBlocker
import kotlinx.coroutines.runBlocking
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class AdBlockFetcherWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams), KodeinAware {
    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     */
    override val kodein by closestKodein(applicationContext)
    //        = Kodein.lazy {
//        import(NetModule.netProvider(applicationContext))
//        import(RepositoryModule.repositoryProvider(applicationContext))
//        import(UsecaseModule.usecaseProvider())
//    }
    private val fetchAdBlockListCase by instance<FetchAdBlockListCase>()

    override fun doWork(): Result {
        runBlocking {
            val list = fetchAdBlockListCase.exec()
            AdBlocker.setAdHosts(list)
        }

        return Result.success()
    }
}
