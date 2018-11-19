package com.no1.taiwan.newsbasket.domain.parameters

import com.no1.taiwan.newsbasket.domain.AnyParameters
import com.no1.taiwan.newsbasket.domain.Parameters

abstract class BaseParams : Parameterable {
    companion object {
        const val PARAM_NAME_FORMAT = "format"

        private const val PARAM_RESPONSE_FORMAT = "json"
    }

    val format = PARAM_RESPONSE_FORMAT

    override fun toApiParam(): Parameters = hashMapOf(PARAM_NAME_FORMAT to format)

    override fun toApiAnyParam(): AnyParameters = hashMapOf(PARAM_NAME_FORMAT to format)
}
