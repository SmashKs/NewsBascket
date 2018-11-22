package com.no1.taiwan.newsbasket.domain.parameters.params

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class TokenParams(
    val token: String = DEFAULT_STR,
    val firebaseToken: String = DEFAULT_STR
) : BaseParams() {
    companion object {
        const val PARAM_NAME_TOKEN = "token"
        const val PARAM_NAME_FIREBASE_TOKEN = "firebase token"
    }

    override fun toApiParam() = hashMapOf(
        PARAM_NAME_TOKEN to token,
        PARAM_NAME_FIREBASE_TOKEN to firebaseToken
    )
}
