package com.no1.taiwan.newsbasket.data.remote.services

import com.no1.taiwan.newsbasket.data.datas.NewsesData
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig.Companion.API_REQUEST
import com.no1.taiwan.newsbasket.domain.Fields
import com.no1.taiwan.newsbasket.domain.Parameters
import kotlinx.coroutines.Deferred
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap

/**
 * Thru [retrofit2.Retrofit] we can just define the interfaces which we want to access for.
 */
interface NewsService {
    @GET("$API_REQUEST/news/")
    fun retrieveNews(@QueryMap params: Parameters): Deferred<NewsesData>

    @FormUrlEncoded
    @POST("$API_REQUEST/subscriber/")
    fun createSubscriber(@FieldMap fields: Fields): Deferred<Boolean>

    @FormUrlEncoded
    @PUT("$API_REQUEST/subscriber/")
    fun modifySubscriber(@FieldMap fields: Fields): Deferred<Boolean>
}
