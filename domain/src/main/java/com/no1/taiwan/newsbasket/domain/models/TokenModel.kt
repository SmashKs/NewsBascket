package com.no1.taiwan.newsbasket.domain.models

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.Token

/**
 * Model object in domain layer to be a bridge object.
 */
data class TokenModel(
    val token: Token = DEFAULT_STR,
    val firebaseToken: Token = DEFAULT_STR
) : Model
