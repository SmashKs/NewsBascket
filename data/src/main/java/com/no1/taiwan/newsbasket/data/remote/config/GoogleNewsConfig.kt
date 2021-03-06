package com.no1.taiwan.newsbasket.data.remote.config

/**
 * The configuration of a remote google news api service.
 */
class GoogleNewsConfig : ApiConfig {
    companion object {
        const val API_REQUEST = "/v2"
        // All basic http api url of Search Music.
        private const val BASE_URL = "https://newsapi.org"
    }

    override val apiBaseUrl = BASE_URL
}
