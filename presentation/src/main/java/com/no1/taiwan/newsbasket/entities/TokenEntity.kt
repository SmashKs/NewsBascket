package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.Token

data class TokenEntity(
    val token: Token = DEFAULT_STR,
    val firebaseToken: Token = DEFAULT_STR
) : Entity
