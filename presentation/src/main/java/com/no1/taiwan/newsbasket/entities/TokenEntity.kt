package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class TokenEntity(
    val token: String = DEFAULT_STR,
    val firebaseToken: String = DEFAULT_STR
) : Entity
