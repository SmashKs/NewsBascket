package com.no1.taiwan.newsbasket.domain.parameters.params

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams

data class TokenParams(
    val token: String? = null,
    val firebaseToken: String? = null
) : BaseParams() {
    companion object {
        const val PARAM_NAME_TOKEN = "token"
        const val PARAM_NAME_FIREBASE_TOKEN = "firebase token"
    }

    override fun toApiParam() = super.toApiParam().apply {
        token?.let { this[PARAM_NAME_TOKEN] = it }
        firebaseToken?.let { this[PARAM_NAME_FIREBASE_TOKEN] = it }
    }
}
