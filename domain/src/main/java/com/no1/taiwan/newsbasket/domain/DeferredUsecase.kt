package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

abstract class DeferredUsecase<T : Any, R : BaseUsecase.RequestValues> : BaseUsecase<R>() {
    suspend fun execute(parameter: R? = null) = run {
        parameter?.let { requestValues = it }

        // Wrapper async again for wrapping the result into [NewsResponse].
        try {
            // If the parent job was cancelled that will happened an exception, that's
            // why we should create a new job instead.
            parentJob = Job() + IO

            GlobalScope.async(parentJob) {
                Success(fetchCase())
            }.await()
        }
        catch (cancel: CancellationException) {
            cancel.printStackTrace()
            Error<T>(msg = cancel.message.orEmpty())
        }
        catch (exception: Exception) {
            exception.printStackTrace()
            Error<T>(msg = exception.message.orEmpty())
        }
    }

    protected abstract fun CoroutineScope.fetchCase(): T

    protected fun attachParameter(λ: suspend (R) -> T) = runBlocking { requireNotNull(requestValues?.run { λ(this) }) }
}
