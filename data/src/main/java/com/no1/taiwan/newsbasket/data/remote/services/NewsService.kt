package com.no1.taiwan.newsbasket.data.remote.services

import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig
import com.no1.taiwan.newsbasket.domain.Parameters
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap

/**
 * Thru [retrofit2.Retrofit] we can just define the interfaces which we want to access for.
 */
interface NewsService {
    @GET("${NewsConfig.API_REQUEST}/news/")
    fun retrieveNews(@QueryMap params: Parameters): Deferred<List<NewsData>>

    @POST("${NewsConfig.API_REQUEST}/subscriber/")
    fun createSubscriber(@QueryMap params: Parameters): Deferred<Boolean>

    @PUT("${NewsConfig.API_REQUEST}/subscriber/")
    fun modifySubscriber(@QueryMap params: Parameters): Deferred<Boolean>
}
