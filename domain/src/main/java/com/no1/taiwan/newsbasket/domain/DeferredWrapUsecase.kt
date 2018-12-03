package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class DeferredWrapUsecase<T : Any, R : BaseUsecase.RequestValues> : DeferredUsecase<T, R>() {
    suspend fun executeWrap(parameter: R? = null) = run {
        // Wrapper async again for wrapping the result into [NewsResponse].
        try {
            // If the parent job was cancelled that will happened an exception, that's
            // why we should create a new job instead.
            parentJob = Job() + Dispatchers.IO

            Success(super.execute(parameter))
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
}
