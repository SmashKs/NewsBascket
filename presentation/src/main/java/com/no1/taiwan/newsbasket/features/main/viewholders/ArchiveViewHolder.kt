package com.no1.taiwan.newsbasket.features.main.viewholders

import android.view.View
import com.no1.taiwan.newsbasket.components.ArchiveVH
import com.no1.taiwan.newsbasket.entities.NewsEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.tv_context
import kotlinx.android.synthetic.main.item_news.tv_title

class ArchiveViewHolder(view: View) : ArchiveVH(view), LayoutContainer {
    /** Returns the root holder view. */
    override val containerView get() = itemView

    override fun initView(model: NewsEntity, position: Int, adapter: Any) {
        tv_title.text = model.title
        tv_context.text = model.content
    }
}
