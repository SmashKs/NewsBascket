package com.no1.taiwan.newsbasket.components.recyclerview

import com.devrapid.adaptiverecyclerview.AdaptiveAdapter
import com.devrapid.adaptiverecyclerview.AdaptiveViewHolder
import com.devrapid.adaptiverecyclerview.IVisitable

typealias NewsViewHolder = AdaptiveViewHolder<MultiTypeFactory, NewsMultiVisitable>
typealias NewsMultiVisitable = IVisitable<MultiTypeFactory>
typealias NewsAdapter = AdaptiveAdapter<MultiTypeFactory, NewsMultiVisitable, NewsViewHolder>
typealias MultiData = MutableList<NewsMultiVisitable>
