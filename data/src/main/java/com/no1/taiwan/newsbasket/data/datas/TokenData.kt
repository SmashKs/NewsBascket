package com.no1.taiwan.newsbasket.data.datas

import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class TokenData(
    val token: String = DEFAULT_STR,
    @SerializedName("firebase_token")
    val firebaseToken: String = DEFAULT_STR
) : Data
