package com.no1.taiwan.newsbasket.domain.parameters.params

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class KeywordsParams(
    val keyword: String = DEFAULT_STR
) : BaseParams() {
    companion object {
        const val PARAM_NAME_KEYWORD = "keyword"
    }

    override fun toApiParam() = hashMapOf(
        PARAM_NAME_KEYWORD to keyword
    )
}
