package com.no1.taiwan.newsbasket.components.recyclerview

import android.content.Context
import android.view.ViewGroup
import com.devrapid.adaptiverecyclerview.AdaptiveDiffUtil
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.AdapterItemTouchHelper
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * The common recyclerview adapter for the multi-type object. Avoid that we create
 * a lots similar boilerplate adapters.
 */
open class MultiTypeAdapter(
    override var dataList: MultiData,
    context: Context,
    private val externalDiffUtil: AdaptiveDiffUtil<MultiTypeFactory, NewsMultiVisitable>? = null
) : NewsAdapter(), KodeinAware, AdapterItemTouchHelper {
    override var typeFactory: MultiTypeFactory
        get() = multiTypeFactory
        set(_) = throw UnsupportedOperationException("We don't allow this method to use!")
    override var diffUtil: AdaptiveDiffUtil<MultiTypeFactory, NewsMultiVisitable>
        get() = externalDiffUtil ?: super.diffUtil
        set(_) = throw UnsupportedOperationException("We don't allow this method to use!")
    override val kodein by closestKodein(context)
    protected var viewType = DEFAULT_INT
    private val multiTypeFactory by instance<MultiTypeFactory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        this.viewType = viewType

        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onItemSwiped(position: Int) {
        dropAt(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
    }
}
