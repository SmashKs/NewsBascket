package com.no1.taiwan.newsbasket.entities

import android.os.Parcelable
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import kotlinx.android.parcel.Parcelize

/**
 * Model object in domain layer to be a bridge object.
 */
@Parcelize
data class NewsSourceEntity(
    val category: String = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    val description: String = DEFAULT_STR,
    val id: String = DEFAULT_STR,
    val language: String = DEFAULT_STR,
    val name: String = DEFAULT_STR,
    val url: String = DEFAULT_STR
) : Entity, Parcelable
