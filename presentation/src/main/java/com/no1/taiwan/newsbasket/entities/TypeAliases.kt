package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.domain.models.NewsArticleModel
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.NewsSourceModel
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.entities.mappers.Mapper

typealias PresentationMapper = Mapper<*, *>
typealias PresentationMapperPool = Map<Class<out PresentationMapper>, PresentationMapper>

// Mapper

typealias PresentationNewsMapper = Mapper<NewsModel, NewsEntity>
typealias PresentationTokenMapper = Mapper<TokenModel, TokenEntity>
typealias PresentationArticleMapper = Mapper<NewsArticleModel, NewsArticleEntity>
typealias PresentationSourceMapper = Mapper<NewsSourceModel, NewsSourceEntity>

// Entity

typealias Keywords = List<String>
typealias Newses = List<NewsEntity>
typealias Articles = List<NewsArticleEntity>
typealias Sources = List<NewsSourceEntity>
