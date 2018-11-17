package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.data.datas.mappers.Mapper
import com.no1.taiwan.newsbasket.domain.models.NewsModel

typealias DataMapper = Mapper<*, *>
typealias MapperPool = Map<Class<out DataMapper>, DataMapper>

typealias DataNewsMapper = Mapper<NewsData, NewsModel>
