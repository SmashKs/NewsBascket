package com.no1.taiwan.newsbasket.domain

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * A base abstract class for wrapping a coroutine [Deferred] object and do the error handling
 * when an error or cancellation happened.
 */
abstract class DeferredUsecase<T : Any, R : BaseUsecase.RequestValues> : BaseUsecase<R>, CoroutineScope {
    /** The main job for the top schedule. */
    private val parentJob get() = SupervisorJob()
    /** Context of this scope. */
    override val coroutineContext get() = parentJob + IO

    internal abstract fun acquireCase(parentJob: CoroutineContext): Deferred<T>

    @Throws(CancellationException::class)
    open suspend fun execute(parameter: R? = null) = withContext(coroutineContext) {
        parameter?.let { requestValues = it }

        // If the parent job was cancelled that will happened an exception, that's
        // why we should create a new job instead.
        awaitRawData()
    }

    private suspend fun awaitRawData() = acquireCase(coroutineContext).await()

    protected fun attachParameter(λ: suspend (R) -> Deferred<T>) =
        runBlocking(coroutineContext) { requireNotNull(requestValues?.run { λ(this) }) }

    /**
     * Aborts the job processing when its state is active and initialized.
     */
    open fun abort() = coroutineContext.takeIf(CoroutineContext::isActive)?.cancelChildren()
}
