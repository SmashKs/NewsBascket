@file:Suppress("NOTHING_TO_INLINE")

package com.no1.taiwan.newsbasket.ext

import com.devrapid.kotlinknifer.WeakRef
import com.devrapid.kotlinshaver.castOrNull
import com.no1.taiwan.newsbasket.bases.LoadView
import com.no1.taiwan.newsbasket.domain.NewsResponse
import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Loading
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success

/**
 * Check the [NewsResponse]'s changing and do the corresponding reaction. Here're three data
 * type [Loading], [Success], and [Error].
 *
 * - [Loading] state will show the loading view.
 * - [Success] state will extract the data from the inside class to be used [successBlock].
 * - [Error] state will show the error view and msg to the user. Also, you can use [errorBlock] manually.
 */
internal class LoadingBuilder<D>(response: NewsResponse<D>) {
    var isShowLoading = true
    var isShowError = true
    var isHideLoading = true
    var successBlock by WeakRef<(D) -> Unit>()
    var completedBlock by WeakRef<() -> Unit>()
    var errorBlock by WeakRef<(String) -> Unit>()
    private val response by WeakRef(response)

    fun peel(loadView: LoadView) {
        when (response) {
            is NewsResponse.Loading<*> -> if (isShowLoading) loadView.showLoading()
            is NewsResponse.Success<D> -> {
                response?.data?.run { successBlock?.invoke(this) }
                if (isShowLoading && isHideLoading) loadView.hideLoading()
                if (isShowLoading) loadView.hideLoading()
                completedBlock?.invoke()
            }
            is NewsResponse.Error<*> -> {
                val res = castOrNull<NewsResponse.Error<*>>(response)

                if (isShowLoading) loadView.hideLoading()
                if (isShowError) loadView.showError(res?.msg.orEmpty())
                errorBlock?.invoke(res?.msg.orEmpty())
            }
        }
    }
}

/**
 * Check the [NewsResponse]'s changing and do the corresponding reaction.
 */
internal inline infix fun <D> NewsResponse<D>.peel(noinline λ: (D) -> Unit) =
    LoadingBuilder(this).apply { successBlock = λ }

/**
 * Check the [NewsResponse]'s changing and do the corresponding reaction. For the situation which we need
 * to use multi-requests in the same time.
 */
internal inline infix fun <D> NewsResponse<D>.peelAndContinue(noinline successBlock: (D) -> Unit) =
    peel(successBlock).apply { isHideLoading = false }

/**
 * Check the [NewsResponse]'s changing and do the corresponding reaction without showing the loading view.
 */
internal inline infix fun <D> NewsResponse<D>.peelSkipLoading(noinline successBlock: (D) -> Unit) =
    peel(successBlock).apply { isShowLoading = false }

/**
 * Check the [NewsResponse]'s changing and do the corresponding reaction with error block and completed block.
 */
internal inline infix fun <D> NewsResponse<D>.peelCompleted(noinline completedBlock: () -> Unit) =
    LoadingBuilder(this).apply { this.completedBlock = completedBlock }

internal inline infix fun <D> LoadingBuilder<D>.happenError(noinline errorBlock: (String) -> Unit) = apply {
    this.errorBlock = errorBlock
}

internal inline infix fun <D> LoadingBuilder<D>.finally(noinline completedBlock: () -> Unit) = apply {
    this.completedBlock = completedBlock
}

/**
 * Execute the checking [NewsResponse]'s process with the triggering [LoadView].
 */
internal inline infix fun <D> LoadingBuilder<D>.doWith(loadView: LoadView) = peel(loadView)
