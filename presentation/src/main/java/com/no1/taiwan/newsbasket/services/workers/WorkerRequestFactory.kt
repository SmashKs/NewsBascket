package com.no1.taiwan.newsbasket.services.workers

import android.content.res.Resources
import androidx.collection.ArrayMap
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder

object WorkerRequestFactory {
    const val WORKER_INIT = "worker for initializing"

    private val mapping by lazy {
        ArrayMap<String, OneTimeWorkRequest>().apply {
            put(WORKER_INIT, OneTimeWorkRequestBuilder<FirstInitWorker>().build())
        }
    }

    fun getWorkerRequest(tag: String) = mapping[tag] ?: throw Resources.NotFoundException()
}
