package com.no1.taiwan.newsbasket.data.remote

import com.no1.taiwan.newsbasket.data.remote.config.GoogleNewsConfig
import com.no1.taiwan.newsbasket.data.remote.config.NewsConfig

/**
 * Factory that creates different implementations of [com.no1.taiwan.newsbasket.data.remote.config.ApiConfig].
 */
class RestfulApiFactory {
    fun createNewsConfig() = NewsConfig()
    fun createGoogleNewsConfig() = GoogleNewsConfig()
}
