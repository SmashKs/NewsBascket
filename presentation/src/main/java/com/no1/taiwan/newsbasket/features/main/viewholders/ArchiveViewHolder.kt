package com.no1.taiwan.newsbasket.features.main.viewholders

import android.view.View
import com.hwangjr.rxbus.RxBus
import com.no1.taiwan.newsbasket.components.ArchiveVH
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.ext.loadByAny
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.ccv_news
import kotlinx.android.synthetic.main.item_news.iv_thumbnail
import kotlinx.android.synthetic.main.item_news.tv_context
import kotlinx.android.synthetic.main.item_news.tv_title
import org.jetbrains.anko.sdk25.coroutines.onClick

class ArchiveViewHolder(view: View) : ArchiveVH(view), LayoutContainer {
    /** Returns the root holder view. */
    override val containerView get() = itemView

    override fun initView(model: NewsEntity, position: Int, adapter: Any) {
        model.urlToImage?.takeIf(String::isNotBlank)?.let { iv_thumbnail.loadByAny(it) }
        tv_title.text = model.title
        tv_context.text = model.content

        /** @postTo [com.no1.taiwan.newsbasket.features.main.ArchiveFragment.openBrowserAndRemoveClicked] */
        ccv_news.onClick {
            RxBus.get().post("open browser", hashMapOf(
                "url" to model.url,
                "title" to model.title,
                "index" to position))
        }
    }
}
