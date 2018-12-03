package com.no1.taiwan.newsbasket.domain

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

abstract class DeferredUsecase<T : Any, R : BaseUsecase.RequestValues> : BaseUsecase<R> {
    protected lateinit var parentJob: CoroutineContext

    @Throws(CancellationException::class)
    open suspend fun execute(parameter: R? = null) = run {
        parameter?.let { requestValues = it }

        // If the parent job was cancelled that will happened an exception, that's
        // why we should create a new job instead.
        parentJob = Job() + IO
        GlobalScope.async(parentJob) { fetchCase() }.await()
    }

    internal abstract fun CoroutineScope.fetchWrapCase(): Deferred<T>

    protected suspend fun CoroutineScope.fetchCase() = fetchWrapCase().await()

    protected fun attachParameter(λ: suspend (R) -> T) = runBlocking { requireNotNull(requestValues?.run { λ(this) }) }

    protected fun attachParameterWrap(λ: suspend (R) -> Deferred<T>) =
        runBlocking { requireNotNull(requestValues?.run { λ(this) }) }

    open fun abort() {
        if (::parentJob.isInitialized && parentJob.isActive)
            parentJob.cancel()
    }
}
