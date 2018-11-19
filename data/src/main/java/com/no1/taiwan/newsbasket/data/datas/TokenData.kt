package com.no1.taiwan.newsbasket.data.datas

import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.Token

data class TokenData(
    val token: Token = DEFAULT_STR,
    @SerializedName("firebase_token")
    val firebaseToken: Token = DEFAULT_STR
) : Data
