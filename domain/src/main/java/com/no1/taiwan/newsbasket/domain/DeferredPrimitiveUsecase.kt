package com.no1.taiwan.newsbasket.domain

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

abstract class DeferredPrimitiveUsecase<T : Any, R : BaseUsecase.RequestValues> : BaseUsecase<R>() {
    @Throws(CancellationException::class)
    suspend fun execute(parameter: R? = null) = run {
        parameter?.let { requestValues = it }

        // If the parent job was cancelled that will happened an exception, that's
        // why we should create a new job instead.
        parentJob = Job() + IO
        GlobalScope.async(parentJob) { fetchCase() }.await()
    }

    protected abstract fun CoroutineScope.fetchCase(): T

    protected fun attachParameter(λ: suspend (R) -> T) = runBlocking { requireNotNull(requestValues?.run { λ(this) }) }
}
