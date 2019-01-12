package com.no1.taiwan.newsbasket.domain.parameters.params.googlenews

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.takeUnlessDefault

data class TopParams(
    val country: String = DEFAULT_STR,
    val category: String = DEFAULT_STR,
    val sources: String = DEFAULT_STR,
    val q: String = DEFAULT_STR
) : BasePagingParams() {
    override fun toApiParam() = super.toApiParam().apply {
        country.takeUnlessDefault { put(PARAM_COUNTRY, it) }
        category.takeUnlessDefault { put(PARAM_CATEGORY, it) }
        sources.takeUnlessDefault { put(PARAM_SOURCES, it) }
        q.takeUnlessDefault { put(PARAM_Q, it) }
    }

    override fun toApiAnyParam() = super.toApiAnyParam().apply {
        country.takeUnlessDefault { put(PARAM_COUNTRY, it) }
        category.takeUnlessDefault { put(PARAM_CATEGORY, it) }
        sources.takeUnlessDefault { put(PARAM_SOURCES, it) }
        q.takeUnlessDefault { put(PARAM_Q, it) }
    }
}
