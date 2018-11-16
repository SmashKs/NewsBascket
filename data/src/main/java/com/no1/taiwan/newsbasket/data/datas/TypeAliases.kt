package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.data.datas.mappers.Mapper
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.TestModel

typealias DataMapper = Mapper<*, *>
typealias MapperPool = Map<Class<out DataMapper>, DataMapper>

typealias DataTestMapper = Mapper<TestData, TestModel>
typealias DataNewsMapper = Mapper<NewsData, NewsModel>
