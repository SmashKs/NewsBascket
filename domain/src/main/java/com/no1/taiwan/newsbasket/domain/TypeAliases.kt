package com.no1.taiwan.newsbasket.domain

import com.no1.taiwan.newsbasket.domain.models.NewsArticleModel
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.NewsSourceModel
import com.no1.taiwan.newsbasket.ext.const.LookUp

// Generic with Type Parameters

typealias Parameters = LookUp<String>
typealias AnyParameters = HashMap<String, Any>
typealias Fields = LookUp<String>

// Model

typealias Newses = List<NewsModel>
typealias Articles = List<NewsArticleModel>
typealias Sources = List<NewsSourceModel>
