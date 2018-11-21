package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.components.recyclerview.NewsMultiVisitable
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UniqueId

data class KeywordEntity(
    val keyword: String = DEFAULT_STR,
    var id: UniqueId = DEFAULT_LONG
) : Entity, NewsMultiVisitable {
    override fun type(typeFactory: MultiTypeFactory) = typeFactory.type(this)
}
