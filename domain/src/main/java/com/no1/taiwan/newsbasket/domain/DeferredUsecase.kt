package com.no1.taiwan.newsbasket.domain

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

/**
 * A base abstract class for wrapping a coroutine [Deferred] object and do the error handling
 * when an error or cancellation happened.
 */
abstract class DeferredUsecase<T : Any, R : BaseUsecase.RequestValues> : BaseUsecase<R> {
    /** The main job for the top schedule. */
    protected lateinit var parentJob: CoroutineContext

    internal abstract fun acquireCase(parentJob: CoroutineContext): Deferred<T>

    @Throws(CancellationException::class)
    open suspend fun execute(parameter: R? = null) = run {
        parameter?.let { requestValues = it }

        // If the parent job was cancelled that will happened an exception, that's
        // why we should create a new job instead.
        parentJob = Job() + IO
        awaitRawData(parentJob)
    }

    private suspend fun awaitRawData(parentJob: CoroutineContext) = acquireCase(parentJob).await()

    protected fun attachParameter(λ: suspend (R) -> Deferred<T>) =
        runBlocking(parentJob) { requireNotNull(requestValues?.run { λ(this) }) }

    /**
     * Aborts the job processing when its state is active and initialized.
     */
    open fun abort() {
        if (::parentJob.isInitialized && parentJob.isActive)
            parentJob.cancel()
    }
}
