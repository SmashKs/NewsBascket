package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UniqueId

data class NewsEntity(
    val id: UniqueId = DEFAULT_LONG,
    val name: String = DEFAULT_STR
) : Entity
