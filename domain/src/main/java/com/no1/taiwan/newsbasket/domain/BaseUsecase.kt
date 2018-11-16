package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case in the
 * application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * For passing a request parameters [RequestValues] to data layer that set a generic type for wrapping
 * vary data.
 */
abstract class BaseUsecase<R : RequestValues> {
    /** Provide a common parameter variable for the children class. */
    var requestValues: R? = null
    protected lateinit var parentJob: CoroutineContext

    open fun abort() = parentJob.takeIf(CoroutineContext::isActive)?.cancel() ?: Unit

    /** Interface for wrap a data for passing to a request.*/
    interface RequestValues
}
