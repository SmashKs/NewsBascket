package com.no1.taiwan.newsbasket.entities.mappers

import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper

/**
 * A transforming mapping between [NewsModel] and [NewsEntity]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class NewsEntityMapper : PresentationNewsMapper {
    override fun toEntityFrom(model: NewsModel) = model.run { NewsEntity(id, name) }

    override fun toModelFrom(entity: NewsEntity) = entity.run { NewsModel(id, name) }
}
