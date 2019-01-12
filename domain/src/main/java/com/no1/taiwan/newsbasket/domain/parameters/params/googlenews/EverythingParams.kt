package com.no1.taiwan.newsbasket.domain.parameters.params.googlenews

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.takeUnlessDefault

data class EverythingParams(
    val q: String = DEFAULT_STR,
    val sources: String = DEFAULT_STR,
    val domains: String = DEFAULT_STR,
    val excludeDomains: String = DEFAULT_STR,
    val from: String = DEFAULT_STR,
    val to: String = DEFAULT_STR,
    val language: String = DEFAULT_STR,
    val sortBy: String = DEFAULT_STR
) : BasePagingParams() {
    override fun toApiParam() = super.toApiParam().apply {
        q.takeUnlessDefault { put(PARAM_Q, it) }
        sources.takeUnlessDefault { put(PARAM_SOURCES, it) }
        domains.takeUnlessDefault { put(PARAM_DOMAIN, it) }
        excludeDomains.takeUnlessDefault { put(PARAM_EXCLUDE_DOMAIN, it) }
        from.takeUnlessDefault { put(PARAM_FROM, it) }
        to.takeUnlessDefault { put(PARAM_TO, it) }
        language.takeUnlessDefault { put(PARAM_LANGUAGE, it) }
        sortBy.takeUnlessDefault { put(PARAM_SORT_BY, it) }
    }

    override fun toApiAnyParam() = super.toApiAnyParam().apply {
        q.takeUnlessDefault { put(PARAM_Q, it) }
        sources.takeUnlessDefault { put(PARAM_SOURCES, it) }
        domains.takeUnlessDefault { put(PARAM_DOMAIN, it) }
        excludeDomains.takeUnlessDefault { put(PARAM_EXCLUDE_DOMAIN, it) }
        from.takeUnlessDefault { put(PARAM_FROM, it) }
        to.takeUnlessDefault { put(PARAM_TO, it) }
        language.takeUnlessDefault { put(PARAM_LANGUAGE, it) }
        sortBy.takeUnlessDefault { put(PARAM_SORT_BY, it) }
    }
}

