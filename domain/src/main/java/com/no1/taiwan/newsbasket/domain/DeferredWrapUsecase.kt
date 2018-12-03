package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import kotlinx.coroutines.CancellationException

abstract class DeferredWrapUsecase<T : Any, R : BaseUsecase.RequestValues> : DeferredUsecase<T, R>() {
    suspend fun executeWrap(parameter: R? = null) = run {
        // Wrapper async again for wrapping the result into [NewsResponse].
        try {
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
