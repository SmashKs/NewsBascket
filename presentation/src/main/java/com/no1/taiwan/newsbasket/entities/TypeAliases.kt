package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.models.TestModel
import com.no1.taiwan.newsbasket.entities.mappers.Mapper

typealias PresentationTestMapper = Mapper<TestModel, TestEntity>
typealias PresentationNewsMapper = Mapper<NewsModel, NewsEntity>
