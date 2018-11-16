package com.no1.taiwan.newsbasket.domain.models

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class TestModel(
    val items: List<ItemModel> = listOf(),
    val quantity: Int = DEFAULT_INT,
    val res: Int = DEFAULT_INT
) : Model {
    data class ItemModel(
        val id: Int = DEFAULT_INT,
        val itemState: Int = DEFAULT_INT,
        val itemStateName: String = DEFAULT_STR,
        val planId: Int = DEFAULT_INT,
        val planName: String = DEFAULT_STR,
        val brandId: Int = DEFAULT_INT,
        val brandName: String = DEFAULT_STR,
        val brandKana: String = DEFAULT_STR,
        val name: String = DEFAULT_STR,
        val sex: Int = DEFAULT_INT,
        val priorityReserveItemId: Int = DEFAULT_INT,
        val partnershipCompanyId: Int = DEFAULT_INT,
        val favorite: Int = DEFAULT_INT,
        val imageFile: List<ImageFileModel> = listOf()
    ) : Model

    data class ImageFileModel(
        val url: String = DEFAULT_STR,
        val thumbUrl: String = DEFAULT_STR,
        val largeUrl: String = DEFAULT_STR
    ) : Model
}
