package com.no1.taiwan.newsbasket.data.remote.services

import com.no1.taiwan.newsbasket.data.datas.GoogleNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsSourceInfoData
import com.no1.taiwan.newsbasket.data.remote.config.GoogleNewsConfig.Companion.API_REQUEST
import com.no1.taiwan.newsbasket.domain.Parameters
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Thru [retrofit2.Retrofit] we can just define the interfaces which we want to access for.
 * Using prefix name (retrieve), (insert), (replace), (release)
 */
interface GoogleNewsService {
    @GET("$API_REQUEST/top-headlines")
    fun retrieveTopNewsAsync(@QueryMap params: Parameters): Deferred<GoogleNewsInfoData>

    @GET("$API_REQUEST/everything")
    fun retrieveEverythingAsync(@QueryMap params: Parameters): Deferred<GoogleNewsInfoData>

    @GET("$API_REQUEST/sources")
    fun retrieveSourcesAsync(@QueryMap params: Parameters): Deferred<GoogleNewsSourceInfoData>
}
