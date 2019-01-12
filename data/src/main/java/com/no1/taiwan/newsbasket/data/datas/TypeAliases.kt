package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.data.datas.mappers.Mapper
import com.no1.taiwan.newsbasket.domain.models.GoogleNewsArticleModel
import com.no1.taiwan.newsbasket.domain.models.GoogleNewsSourceModel
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.TokenModel

typealias DataMapper = Mapper<*, *>
typealias MapperPool = Map<Class<out DataMapper>, DataMapper>

typealias DataNewsMapper = Mapper<NewsData, NewsModel>
typealias DataTokenMapper = Mapper<TokenData, TokenModel>
typealias DataArticleMapper = Mapper<GoogleNewsArticleData, GoogleNewsArticleModel>
typealias DataSourceMapper = Mapper<GoogleNewsSourceData, GoogleNewsSourceModel>

// Data

typealias Newses = List<NewsData>
typealias Keywords = List<KeywordData>
typealias Articles = List<GoogleNewsArticleData>
typealias NewsSources = List<GoogleNewsSourceData>
