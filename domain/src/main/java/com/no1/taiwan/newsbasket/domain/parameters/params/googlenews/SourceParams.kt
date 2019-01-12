package com.no1.taiwan.newsbasket.domain.parameters.params.googlenews

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.takeUnlessDefault

data class SourceParams(
    val category: String = DEFAULT_STR,
    val language: String = DEFAULT_STR,
    val country: String = DEFAULT_STR
) : BasePagingParams() {
    override fun toApiParam() = super.toApiParam().apply {
        category.takeUnlessDefault { put(PARAM_CATEGORY, it) }
        language.takeUnlessDefault { put(PARAM_LANGUAGE, it) }
        country.takeUnlessDefault { put(PARAM_COUNTRY, it) }
    }

    override fun toApiAnyParam() = super.toApiAnyParam().apply {
        category.takeUnlessDefault { put(PARAM_CATEGORY, it) }
        language.takeUnlessDefault { put(PARAM_LANGUAGE, it) }
        country.takeUnlessDefault { put(PARAM_COUNTRY, it) }
    }
}
