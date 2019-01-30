package com.no1.taiwan.newsbasket.ext.internet

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceResponse
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import okhttp3.HttpUrl
import java.io.ByteArrayInputStream

object AdBlocker {
    private var adHosts: List<String> = emptyList()

    fun setAdHosts(blockList: List<String>) {
        adHosts = blockList
    }

    fun isAd(url: String): Boolean {
        val httpUrl = HttpUrl.parse(url)
        return isAdHost(httpUrl?.host() ?: DEFAULT_STR)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun createEmptyResource() = WebResourceResponse("text/plain", "utf-8", ByteArrayInputStream("".toByteArray()))

    private fun isAdHost(host: String): Boolean {
        if (host.isEmpty()) {
            return false
        }

        val index = host.indexOf(".")
        return index >= 0 && (adHosts.contains(host) || index + 1 < host.length && isAdHost(host.substring(index + 1)))
    }
}
