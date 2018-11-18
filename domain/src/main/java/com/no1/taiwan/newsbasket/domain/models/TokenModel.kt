package com.no1.taiwan.newsbasket.domain.models

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

/**
 * Model object in domain layer to be a bridge object.
 */
data class TokenModel(
    val token: String = DEFAULT_STR,
    val firebaseToken: String = DEFAULT_STR
) : Model
