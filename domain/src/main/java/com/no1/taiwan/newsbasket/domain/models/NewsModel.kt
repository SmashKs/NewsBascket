package com.no1.taiwan.newsbasket.domain.models

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UniqueId

/**
 * Model object in domain layer to be a bridge object.
 */
data class NewsModel(
    var id: UniqueId = DEFAULT_LONG,
    var name: String = DEFAULT_STR
) : Model
