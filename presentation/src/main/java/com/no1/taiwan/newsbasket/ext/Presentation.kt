package com.no1.taiwan.newsbasket.ext

import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.domain.NewsResponse
import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Loading
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import kotlinx.coroutines.CoroutineScope

/**
 * A transformer wrapper for encapsulating the [RespMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 *
 * Also, unboxing the [NewsResponse] and obtaining the data inside of the [NewsResponse], then return the
 * data to [RespMutableLiveData].
 */
suspend fun <E, R> RespMutableLiveData<R>.reqDataMap(
    usecaseRes: suspend CoroutineScope.() -> NewsResponse<E>,
    transformBlock: (E) -> R
) = preProc {
    // Fetching the data from the data layer.
    postValue(tryResp {
        val entity = usecaseRes()

        entity.data?.let(transformBlock)?.let { Success(it) } ?: Error<R>(msg = "Don't have any response.")
    })
}

/**
 * A transformer wrapper for encapsulating the [RespMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 */
infix fun <E> RespMutableLiveData<E>.reqData(usecaseRes: suspend CoroutineScope.() -> NewsResponse<E>) =
    preProc {
        // Fetching the data from the data layer.
        postValue(tryResp { usecaseRes() })
    }

/**
 * Pre-process does that showing the loading view.
 */
private fun <E> RespMutableLiveData<E>.preProc(block: suspend CoroutineScope.() -> Unit) = gLaunch {
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
private inline fun <E> tryResp(block: () -> NewsResponse<E>) = try {
    block()
}
catch (e: Exception) {
    e.printStackTrace()
    Error<E>(msg = e.message ?: "Unknown error has happened.")
}
