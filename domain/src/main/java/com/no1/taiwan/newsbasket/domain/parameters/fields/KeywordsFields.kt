package com.no1.taiwan.newsbasket.domain.parameters.fields

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.Token

data class KeywordsFields(
    val token: Token = DEFAULT_STR,
    val firebaseToken: Token = DEFAULT_STR,
    val keywords: String = DEFAULT_STR
) : BaseParams() {
    companion object {
        const val PARAM_NAME_TOKEN = "token"
        const val PARAM_NAME_FIREBASE_TOKEN = "firebase_token"
        const val PARAM_NAME_KEYWORDS = "keywords"
    }

    override fun toApiParam() = hashMapOf(
        PARAM_NAME_TOKEN to token,
        PARAM_NAME_FIREBASE_TOKEN to firebaseToken,
        PARAM_NAME_KEYWORDS to keywords
    )
}
