package com.no1.taiwan.newsbasket.domain.parameters.params.googlenews

import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.takeUnlessDefault

abstract class BasePagingParams : Parameterable {
    companion object {
        const val PARAM_COUNTRY = "country"
        const val PARAM_CATEGORY = "category"
        const val PARAM_SOURCES = "sources"
        const val PARAM_Q = "q"
        const val PARAM_LANGUAGE = "language"
        const val PARAM_DOMAIN = "domain"
        const val PARAM_EXCLUDE_DOMAIN = "excludeDomain"
        const val PARAM_FROM = "from"
        const val PARAM_TO = "to"
        const val PARAM_SORT_BY = "sortBy"
        // Common Params
        const val PARAM_PAGE_SIZE = "pageSize"
        const val PARAM_PAGE = "page"
        const val PARAM_API_KEY = "apiKey"
    }

    val pageSize = DEFAULT_INT
    val page = DEFAULT_INT
    val apiKey = DEFAULT_STR

    override fun toApiParam() = hashMapOf<String, String>().apply {
        pageSize.takeUnlessDefault { put(PARAM_PAGE_SIZE, it.toString()) }
        page.takeUnlessDefault { put(PARAM_PAGE, it.toString()) }
        apiKey.takeUnlessDefault { put(PARAM_API_KEY, it) }
    }

    override fun toApiAnyParam() = hashMapOf<String, Any>().apply {
        pageSize.takeUnlessDefault { put(PARAM_PAGE_SIZE, it) }
        page.takeUnlessDefault { put(PARAM_PAGE, it) }
        apiKey.takeUnlessDefault { put(PARAM_API_KEY, it) }
    }
}
