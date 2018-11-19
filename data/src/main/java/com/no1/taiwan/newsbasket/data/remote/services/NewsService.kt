package com.no1.taiwan.newsbasket.data.remote.services

import com.no1.taiwan.newsbasket.data.datas.NewsesData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig.Companion.API_REQUEST
import com.no1.taiwan.newsbasket.domain.Fields
import com.no1.taiwan.newsbasket.domain.Parameters
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap

/**
 * Thru [retrofit2.Retrofit] we can just define the interfaces which we want to access for.
 */
interface NewsService {
    @GET("$API_REQUEST/news/")
    fun retrieveNews(@QueryMap params: Parameters): Deferred<NewsesData>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("$API_REQUEST/subscriber/")
    fun createSubscriber(@Body fields: Fields): Deferred<TokenData>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("$API_REQUEST/subscriber/")
    fun updateSubscriber(@Body fields: Fields): Deferred<Boolean>
}
