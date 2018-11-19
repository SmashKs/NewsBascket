package com.no1.taiwan.newsbasket.data.remote.config

import com.no1.taiwan.newsbasket.data.BuildConfig

/**
 * The configuration of a remote service.
 */
class NewsConfig : ApiConfig {
    companion object {
        const val CONTENT_TYPE_JSON = "Content-Type: application/json;charset=UTF-8"
        const val API_REQUEST = BuildConfig.API_REQUEST
        // All basic http api url of Search Music.
        private const val BASE_URL = BuildConfig.URL_SERVER
    }

    override val apiBaseUrl = BASE_URL
}
