package com.no1.taiwan.newsbasket.ext

import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.domain.NewsResponse
import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Loading
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import kotlinx.coroutines.CoroutineScope

/**
 * A transformer wrapper for encapsulating the [ResponseMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 *
 * Also, unboxing the [NewsResponse] and obtaining the data inside of the [NewsResponse], then return the
 * data to [ResponseMutableLiveData].
 */
suspend fun <E, R> ResponseMutableLiveData<R>.requestDataMap(
    usecaseRes: suspend CoroutineScope.() -> NewsResponse<E>,
    transformBlock: (E) -> R
) = preProcess {
    // Fetching the data from the data layer.
    postValue(tryResponse {
        val entity = usecaseRes()

        entity.data?.let(transformBlock)?.let { Success(it) } ?: Error<R>(msg = "Don't have any response.")
    })
}

/**
 * A transformer wrapper for encapsulating the [ResponseMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 */
infix fun <E> ResponseMutableLiveData<E>.requestData(usecaseRes: suspend CoroutineScope.() -> NewsResponse<E>) =
    preProcess {
        // Fetching the data from the data layer.
        postValue(tryResponse { usecaseRes() })
    }

/**
 * Pre-process does that showing the loading view.
 */
private fun <E> ResponseMutableLiveData<E>.preProcess(block: suspend CoroutineScope.() -> Unit) = gLaunch {
    apply {
        // Opening the loading view.
        postValue(Loading())
        // Fetching the data from the data layer.
        block()
    }
}

/**
 * Wrapping the `try catch` and ignoring the return value.
 */
private inline fun <E> tryResponse(block: () -> NewsResponse<E>) = try {
    block()
}
catch (e: Exception) {
    e.printStackTrace()
    Error<E>(msg = e.message ?: "Unknown error has happened.")
}
