package com.no1.taiwan.newsbasket.features.main.viewholders

import android.view.View
import com.devrapid.adaptiverecyclerview.AdaptiveAdapter
import com.no1.taiwan.newsbasket.components.KeywordVH
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_showing.tv_title

class KeywordViewHolder(view: View) : KeywordVH(view), LayoutContainer {
    /** Returns the root holder view. */
    override val containerView get() = itemView

    override fun initView(model: KeywordEntity, position: Int, adapter: AdaptiveAdapter<*, *, *>) {
        tv_title.text = model.keyword
    }
}
