package com.no1.taiwan.newsbasket.domain.parameters

import com.no1.taiwan.newsbasket.domain.AnyParameters
import com.no1.taiwan.newsbasket.domain.Parameters

open class EmptyParams : Parameterable {
    override fun toApiParam() = Parameters()

    override fun toApiAnyParam() = AnyParameters()
}
