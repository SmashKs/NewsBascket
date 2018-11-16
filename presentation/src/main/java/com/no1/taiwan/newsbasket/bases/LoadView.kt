package com.no1.taiwan.newsbasket.bases

import androidx.annotation.UiThread

/**
 * Interface representing a View that will use to load data.
 */
interface LoadView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    @UiThread
    fun showLoading()

    /**
     * Hide a loading view.
     */
    @UiThread
    fun hideLoading()

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    @UiThread
    fun showRetry()

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    @UiThread
    fun hideRetry()

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    @UiThread
    fun showError(message: String)
}
