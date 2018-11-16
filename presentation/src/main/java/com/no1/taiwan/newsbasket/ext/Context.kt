package com.no1.taiwan.newsbasket.ext

import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.ArrayRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.no1.taiwan.newsbasket.App
import org.jetbrains.anko.dimen

fun gContext(): Context = App.appContext.applicationContext

fun gDimen(@DimenRes id: Int) = gContext().dimen(id)

fun gString(@StringRes id: Int): String = gContext().getString(id)

fun gText(@StringRes id: Int): CharSequence = gContext().getText(id)

fun gStringArray(@ArrayRes id: Int): Array<out String> = gContext().resources.getStringArray(id)

fun gTypeArray(@ArrayRes id: Int): TypedArray = gContext().resources.obtainTypedArray(id)

fun gResArray(@ArrayRes id: Int) =
    gStringArray(id).mapIndexed { index, _ -> index to gTypeArray(id) }.toMutableList()

fun gResArrays(@ArrayRes vararg ids: Int) =
    ids.map(::gResArray).reduce { acc, new -> acc.addAll(new);acc }

