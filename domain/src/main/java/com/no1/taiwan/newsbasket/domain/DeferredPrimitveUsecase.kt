package com.no1.taiwan.newsbasket.domain

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

abstract class DeferredPrimitveUsecase<T, R : BaseUsecase.RequestValues> : BaseUsecase<R>() {
    suspend fun execute(parameter: R? = null) = run {
        parameter?.let { requestValues = it }

        GlobalScope.async(IO) { fetchCase() }.await()
    }

    abstract suspend fun fetchCase(): T
}
