package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.data.datas.mappers.Mapper
import com.no1.taiwan.newsbasket.domain.models.NewsArticleModel
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.NewsSourceModel
import com.no1.taiwan.newsbasket.domain.models.TokenModel

typealias DataMapper = Mapper<*, *>
typealias DataMapperPool = Map<Class<out DataMapper>, DataMapper>

typealias DataNewsMapper = Mapper<NewsData, NewsModel>
typealias DataTokenMapper = Mapper<TokenData, TokenModel>
typealias DataArticleMapper = Mapper<GoogleNewsArticleData, NewsArticleModel>
typealias DataSourceMapper = Mapper<GoogleNewsSourceData, NewsSourceModel>

// Data

typealias Newses = List<NewsData>
typealias Keywords = List<KeywordData>
typealias Articles = List<GoogleNewsArticleData>
typealias NewsSources = List<GoogleNewsSourceData>
