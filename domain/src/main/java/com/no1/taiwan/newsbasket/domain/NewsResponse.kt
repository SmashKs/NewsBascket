package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Loading
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

/**
 * The super class response from the data layer. There're three result of the response [Loading],
 * [Success], and [Error] for HTTP result.
 */
sealed class NewsResponse<T> constructor(val data: T? = null) {
    /**
     * A request is still processing from a remote/local service.
     */
    class Loading<T>(data: T? = null) : NewsResponse<T>(data)

    /**
     * A request success getting a result from a remote/local service.
     */
    class Success<T>(data: T? = null) : NewsResponse<T>(data)

    /**
     * A request sent then a remote/local service has happened an error.
     */
    class Error<T>(data: T? = null, val msg: String = DEFAULT_STR) : NewsResponse<T>(data)
}
