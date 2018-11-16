package com.no1.taiwan.newsbasket.components.recyclerview

import android.content.Context
import com.devrapid.adaptiverecyclerview.ViewTypeFactory
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntries
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * A factory for providing the viewholder from an object data type to the recyclerview.
 */
class MultiTypeFactory(context: Context) : ViewTypeFactory(), KodeinAware {
    override var transformMap
        get() = viewHolderEntries.toMap().toMutableMap()
        set(_) = throw UnsupportedOperationException("We don't allow this method to use!")
    override val kodein by closestKodein(context)
    private val viewHolderEntries by instance<ViewHolderEntries>()

    // *** Here are the entity binding the specific hashcode. ***
    fun type(entity: NewsMultiVisitable) = entity.javaClass.hashCode()
}
