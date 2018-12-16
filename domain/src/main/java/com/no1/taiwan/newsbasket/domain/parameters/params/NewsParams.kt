package com.no1.taiwan.newsbasket.domain.parameters.params

import com.no1.taiwan.newsbasket.domain.parameters.BaseParams
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class NewsParams(
    val author: String = DEFAULT_STR,
    val content: String = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    val createdAt: String = DEFAULT_STR,
    val description: String = DEFAULT_STR,
    val publishedAt: String = DEFAULT_STR,
    val title: String = DEFAULT_STR,
    val updatedAt: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    val urlToImage: String = DEFAULT_STR
) : BaseParams() {
    companion object {
        const val PARAM_NAME_AUTHOR = "author"
        const val PARAM_NAME_CONTENT = "content"
        const val PARAM_NAME_COUNTRY = "country"
        const val PARAM_NAME_CREATED = "created time"
        const val PARAM_NAME_DESCRIPTION = "description"
        const val PARAM_NAME_PUBLISHED = "published time"
        const val PARAM_NAME_TITLE = "title"
        const val PARAM_NAME_UPDATED = "updated time"
        const val PARAM_NAME_URL = "url"
        const val PARAM_NAME_IMAGE_URL = "image url"
    }

    override fun toApiParam() = hashMapOf(
        PARAM_NAME_AUTHOR to author,
        PARAM_NAME_CONTENT to content,
        PARAM_NAME_COUNTRY to country,
        PARAM_NAME_CREATED to createdAt,
        PARAM_NAME_DESCRIPTION to description,
        PARAM_NAME_PUBLISHED to publishedAt,
        PARAM_NAME_TITLE to title,
        PARAM_NAME_UPDATED to updatedAt,
        PARAM_NAME_URL to url,
        PARAM_NAME_IMAGE_URL to urlToImage
    )
}
