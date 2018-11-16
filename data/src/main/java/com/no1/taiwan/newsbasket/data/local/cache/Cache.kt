package com.no1.taiwan.newsbasket.data.local.cache

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT

/**
 * This is first layer cache for retrieving from the remote.
 */
interface Cache {
    /**
     * Check the assigned data is expired (too old).
     *
     * @param which
     * @param params
     * @return
     */
    fun isDirty(which: Int, params: Any): Boolean

    /**
     * Set the cache (specific part) to dirty.
     *
     * @param which
     * @param params
     * @return success or fail for setting dirty
     */
    fun setDirty(which: Int, params: Any): Boolean

    /**
     * Check the assigned data has cached.
     *
     * @param which
     * @param params
     * @return
     */
    fun isCached(which: Int, params: Any): Boolean

    /**
     * Update the data in the cache.
     *
     * @param which
     * @param params
     * @param obj
     */
    fun refreshOrCache(which: Int, params: Any, obj: Any? = null)

    /**
     * Get the object from the cache.
     *
     * @param which
     * @param params
     * @return the object you've cached.
     */
    fun obtainCachedObj(which: Int, params: Any): Any?

    /**
     * Clean the assigned column by [which] and [params].
     *
     * @param which
     * @param params
     */
    fun clearCache(which: Int = DEFAULT_INT, params: Any?)

    /**
     * Show the memory content.
     *
     * @return the memory detail.
     */
    fun describeMemory(): String
}
