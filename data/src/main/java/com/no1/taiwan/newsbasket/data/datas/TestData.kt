package com.no1.taiwan.newsbasket.data.datas

import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class TestData(
    val items: List<ItemData> = listOf(),
    val quantity: Int = DEFAULT_INT,
    val res: Int = DEFAULT_INT
) : Data {
    data class ItemData(
        val id: Int = DEFAULT_INT,
        @SerializedName("item_state")
        val itemState: Int = DEFAULT_INT,
        @SerializedName("item_state_name")
        val itemStateName: String = DEFAULT_STR,
        @SerializedName("plan_id")
        val planId: Int = DEFAULT_INT,
        @SerializedName("plan_name")
        val planName: String = DEFAULT_STR,
        @SerializedName("brand_id")
        val brandId: Int = DEFAULT_INT,
        @SerializedName("brand_name")
        val brandName: String = DEFAULT_STR,
        @SerializedName("brand_kana")
        val brandKana: String = DEFAULT_STR,
        val name: String = DEFAULT_STR,
        val sex: Int = DEFAULT_INT,
        @SerializedName("priority_reserve_item_id")
        val priorityReserveItemId: Int = DEFAULT_INT,
        @SerializedName("partnership_company_id")
        val partnershipCompanyId: Int = DEFAULT_INT,
        val favorite: Int = DEFAULT_INT,
        @SerializedName("image_file")
        val imageFile: List<ImageFileData> = listOf()
    ) : Data

    data class ImageFileData(
        val url: String = DEFAULT_STR,
        @SerializedName("thumb_url")
        val thumbUrl: String = DEFAULT_STR,
        @SerializedName("large_url")
        val largeUrl: String = DEFAULT_STR
    ) : Data
}
