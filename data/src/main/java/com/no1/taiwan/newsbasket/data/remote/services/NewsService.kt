package com.no1.taiwan.newsbasket.data.remote.services

import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig.Companion.API_REQUEST
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig.Companion.CONTENT_TYPE_JSON
import com.no1.taiwan.newsbasket.domain.Fields
import com.no1.taiwan.newsbasket.domain.Parameters
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Thru [retrofit2.Retrofit] we can just define the interfaces which we want to access for.
 * Using prefix name (retrieve), (new), (replace), (release)
 */
interface NewsService {
    @GET("$API_REQUEST/news/")
    fun retrieveNewsAsync(@QueryMap params: Parameters): Deferred<RemoteNewsInfoData>

    @Headers(CONTENT_TYPE_JSON)
    @POST("$API_REQUEST/subscriber/")
    fun newSubscriberAsync(@Body fields: Fields): Deferred<TokenData>

    @Headers(CONTENT_TYPE_JSON)
    @PUT("$API_REQUEST/subscriber/{token}/")
    fun replaceSubscriberAsync(@Path("token") token: String, @Body fields: Fields): Deferred<TokenData>
}
