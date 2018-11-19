package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.entities.mappers.Mapper

// Mapper
typealias PresentationNewsMapper = Mapper<NewsModel, NewsEntity>
typealias PresentationTokenMapper = Mapper<TokenModel, TokenEntity>

// Entity
typealias Keywords = List<String>

typealias Newses = List<NewsEntity>
