package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.data.datas.mappers.Mapper
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.TokenModel

typealias DataMapper = Mapper<*, *>
typealias MapperPool = Map<Class<out DataMapper>, DataMapper>

typealias DataNewsMapper = Mapper<NewsData, NewsModel>
typealias DataTokenMapper = Mapper<TokenData, TokenModel>

// Data
typealias Newses = List<NewsData>

typealias Keywords = List<KeywordData>
