package com.no1.taiwan.newsbasket.data.local.cache

import com.devrapid.kotlinshaver.currentTime
import com.devrapid.kotlinshaver.isNotNull
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.takeIfDefault

/**
 * A cache class for keeping the data temporally into the memory([HashMap]).
 */
open class NewsMemoryCache : AbsCache() {
    companion object {
        private const val CAPACITY = 200
        private const val THRESHOLD = 12L * 60 * 60 * 1000 // a day (time unit is ms)

        const val CATEGORY_NEWS = 43
    }

    /**
     * Keeping the searched result in the cache memory.
     *
     * Structure is as like:
     * ======================================================
     * [Category WATCH_LIST(KEY), List(VALUE)]
     *   ↳ Triple<Parameters, Cached Time, Cached Data(KS)>
     *   ↳ Triple<Parameters, Cached Time, Cached Data(KS)>
     *   ↳ Triple<Parameters, Cached Time, Cached Data(KS)>
     *   ↳ Triple<Parameters, Cached Time, Cached Data(KS)>
     * [Category FEATURE_WATCH_LIST(KEY), List(VALUE)]
     *   ↳ Triple<Parameters, Cached Time, Cached Data(SMASH)>
     *   ↳ Triple<Parameters, Cached Time, Cached Data(SMASH)>
     */
    protected val memory by lazy { CacheMap(CAPACITY) }

    override fun isDirty(which: Int, params: Any) =
        if (isCached(which, params)) {
            isHit(which, params)?.let { currentTime - it.second > THRESHOLD } != false
        }
        else {
            true
        }

    override fun setDirty(which: Int, params: Any): Boolean {
        val cachedItem = isHit(which, params) ?: return false
        val data = Triple(cachedItem.first, DEFAULT_LONG, cachedItem.third)

        memory[which]?.apply {
            remove(cachedItem)
            add(data)
        }

        return true
    }

    override fun isCached(which: Int, params: Any) = isHit(which, params).isNotNull()

    override fun refreshOrCache(which: Int, params: Any, obj: Any?) {
        // TODO(jieyi): 2018/03/08 Currently here is no refresh function.
        if (isHit(which, params).isNotNull()) {
            // *** [isHit(which, params)] must not null object.
            val originData = requireNotNull(isHit(which, params)?.third)

            requireNotNull(memory[which]?.add(Triple(params,
                                                     currentTime,
                                                     obj.takeIf(Any?::isNotNull) ?: originData)))
        }
        else {
            obj.takeIf(Any?::isNotNull)?.let {
                val data = Triple(params, currentTime, it)

                memory[which]?.add(data) ?: memory.put(which, mutableListOf(data))
            }
        }
    }

    override fun obtainCachedObj(which: Int, params: Any) = isHit(which, params)?.third

    override fun clearCache(which: Int, params: Any?) =
        which.takeIfDefault { memory.clear() } ?: let { memory[which]?.remove(params).isNotNull(); Unit }

    override fun describeMemory() = memory.toString()

    protected fun isHit(which: Int, params: Any) = memory[which]?.find { it.first == params }
}
