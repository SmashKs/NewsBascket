package com.no1.taiwan.newsbasket.domain.parameters

data class NewsParams(
    val page: Int = 1
) : BaseParams(), Parameterable {
    companion object {
        const val HASH_NAME_PAGE = "page"
    }

    override fun toApiParam() = super.toApiParam().apply {
        put(HASH_NAME_PAGE, page.toString())
    }
}
