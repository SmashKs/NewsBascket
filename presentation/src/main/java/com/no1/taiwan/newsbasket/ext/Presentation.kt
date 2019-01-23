package com.no1.taiwan.newsbasket.ext

import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.domain.NewsResponse
import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Loading
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success

/**
 * A transformer wrapper for encapsulating the [RespMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 *
 * Also, unboxing the [NewsResponse] and obtaining the data inside of the [NewsResponse], then return the
 * data to [RespMutableLiveData].
 */
fun <E, R> RespMutableLiveData<R>.reqDataMap(
    usecaseRes: suspend () -> NewsResponse<E>,
    transformBlock: (E) -> R
) = preProc {
    // Fetching the data from the data layer.
    tryResp {
        val data = usecaseRes().data ?: return@tryResp Error<R>(msg = "Don't have any response.")
        Success(transformBlock(data))
    }.let(this@reqDataMap::postValue)
}

/**
 * A transformer wrapper for encapsulating the [RespMutableLiveData]'s state
 * changing and the state becomes [Success] when retrieving a data from Data layer by Kotlin coroutine.
 */
infix fun <E> RespMutableLiveData<E>.reqData(usecaseRes: suspend () -> E) =
    preProc {
        // Fetching the data from the data layer.
        tryResp { Success(usecaseRes()) }.let(this@reqData::postValue)
    }

infix fun <E> RespMutableLiveData<E>.reqDataWrap(usecaseRes: suspend () -> NewsResponse<E>) =
    preProc {
        // Fetching the data from the data layer.
        tryResp { usecaseRes() }.let(this@reqDataWrap::postValue)
    }


/**
 * Pre-process does that showing the loading view.
 */
private fun <E> RespMutableLiveData<E>.preProc(block: suspend () -> Unit) = gLaunch {
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
