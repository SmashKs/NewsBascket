package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.no1.taiwan.newsbasket.BuildConfig
import com.no1.taiwan.newsbasket.data.remote.hasNetwork
import com.no1.taiwan.newsbasket.ext.thirdParty.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

/**
 * To provide the necessary object for the internet model.
 */
object NetModule {
    private const val CacheMaxSize = 10 * 1024 * 1024L
    private const val AWeekTime = 60 * 60 * 24 * 7

    fun netProvider(context: Context) = Module("Net Module") {
        bind<Converter.Factory>() with singleton { GsonConverterFactory.create(instance()) }
        bind<CallAdapter.Factory>() with singleton { CoroutineCallAdapterFactory() }
        bind<Cache>() with singleton { Cache(context.cacheDir, CacheMaxSize /* 10 MiB */) }
        bind<OkHttpClient>() with singleton {
            OkHttpClient.Builder()
                .cache(instance())
                // Keep the internet result into the cache.
                .addInterceptor {
                    // Get the request from the chain.
                    var request = it.request()

                    /*
                    *  Leveraging the advantage of using Kotlin,
                    *  we initialize the request and change its header depending on whether
                    *  the device is connected to Internet or not.
                    */
                    request = if (hasNetwork(context)!!) {
                        /*
                        *  If there is Internet, get the cache that was stored 5 seconds ago.
                        *  If the cache is older than 5 seconds, then discard it,
                        *  and indicate an error in fetching the response.
                        *  The 'max-age' attribute is responsible for this behavior.
                        */
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    }
                    else {
                        /*
                        *  If there is no Internet, get the cache that was stored 7 days ago.
                        *  If the cache is older than 7 days, then discard it,
                        *  and indicate an error in fetching the response.
                        *  The 'max-stale' attribute is responsible for this behavior.
                        *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache
                        *  only instead.
                        */
                        request.newBuilder().header("Cache-Control",
                                                    "public, only-if-cached, max-stale=$AWeekTime").build()
                    }
                    // End of if-else statement

                    // Add the modified request to the chain.
                    it.proceed(request)
                }
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(OkHttpProfilerInterceptor())  // For OkHttp Profiler plugins.
                        addInterceptor(HttpLoggingInterceptor().setLevel(BODY))  // For print to logcat.
                    }
                }
                .build()
        }
        bind<Builder>() with singleton {
            Builder()
                .addConverterFactory(instance())
                .addCallAdapterFactory(instance())
                .client(instance())
        }
    }
}
