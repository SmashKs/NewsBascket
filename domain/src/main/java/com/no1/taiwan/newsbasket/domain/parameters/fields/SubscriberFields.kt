package com.no1.taiwan.newsbasket.domain.parameters.fields

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class SubscriberFields(
    val firebaseToken: String = DEFAULT_STR,
    val keywords: String = DEFAULT_STR
) : BaseParams(),
    Parameterable {
    companion object {
        const val PARAM_NAME_FIREBASE_TOKEN = "firebase_token"
        const val PARAM_NAME_KEYWORDS = "keywords"
    }

    override fun toApiParam() = hashMapOf(
        PARAM_NAME_FIREBASE_TOKEN to firebaseToken,
        PARAM_NAME_KEYWORDS to keywords
    )
}
