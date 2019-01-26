@file:Suppress("NOTHING_TO_INLINE")

package com.no1.taiwan.newsbasket.ext

import android.app.Activity
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes
import com.devrapid.kotlinknifer.gone
import com.devrapid.kotlinknifer.visible
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import org.jetbrains.anko.find
import org.jetbrains.anko.findOptional

fun Activity.findViewStub(@IdRes stub: Int, @IdRes realView: Int) = findOptional<ViewStub>(stub) ?: find<View>(realView)

//region Show View Stub
fun Activity.showViewStub(@IdRes stub: Int, @IdRes realView: Int, options: (View.() -> Unit)? = null) =
    findViewStub(stub, realView).apply {
        visible()
        bringToFront()
        invalidate()
        options?.let(this::apply)
}

inline fun Activity.showLoadingView() = showViewStub(R.id.vs_loading, R.id.v_loading, null)

inline fun Activity.showErrorView(errorMsg: String = DEFAULT_STR) = showViewStub(R.id.vs_error, R.id.v_error) {
    find<TextView>(R.id.tv_error_msg).text = errorMsg
}

inline fun Activity.showRetryView(noinline retryListener: ((View) -> Unit)? = null) =
    showViewStub(R.id.vs_retry, R.id.v_retry) { retryListener?.let(this::setOnClickListener) }
//endregion

//region Hide View Stub
fun Activity.hideViewStub(@IdRes stub: Int, @IdRes realView: Int, options: (View.() -> Unit)? = null) =
    findViewStub(stub, realView).apply { options?.invoke(this) }.gone()

inline fun Activity.hideLoadingView() = hideViewStub(R.id.vs_loading, R.id.v_loading)

inline fun Activity.hideErrorView() = hideViewStub(R.id.vs_error, R.id.v_error)

inline fun Activity.hideRetryView() = hideViewStub(R.id.vs_retry, R.id.v_retry).apply {
    find<Button>(R.id.btn_retry).takeIf { it.hasOnClickListeners() }?.setOnClickListener(null)
}
//endregion
