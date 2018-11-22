package com.no1.taiwan.newsbasket.domain.parameters.queries

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams

data class NewsQueries(
    val page: Int = 1
) : BaseParams() {
    companion object {
        const val PARAM_NAME_PAGE = "page"
    }

    override fun toApiParam() = super.toApiParam().apply {
        put(PARAM_NAME_PAGE, page.toString())
    }
}
